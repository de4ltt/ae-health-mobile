package feo.health.mapper

import com.google.devtools.ksp.isPrivate
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate

/**
 * Kotlin Symbol Processing (KSP) processor that generates two-way extension mapping functions
 * for classes annotated with [@Mapper] and implementing [IMapper].
 *
 * Automatically generates shortened mapping functions by subtracting the common CamelCase word
 * intersection between source and target classes. It also generates deprecation wrappers
 * for backward compatibility with previous long-named functions.
 *
 * @property codeGenerator KSP tool used to create new file resources.
 * @property logger KSP diagnostic logger tool.
 */
class MapperProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : SymbolProcessor {

    /**
     * Processes annotations on symbols in the source codebase.
     *
     * @param resolver KSP environment resolver.
     * @return List of unresolved annotated symbols.
     */
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val annotationName = "feo.health.mapper.Mapper"
        val symbols = resolver.getSymbolsWithAnnotation(annotationName)
        val invalidSymbols = symbols.filter { !it.validate() }.toList()
        symbols.filter { it is KSClassDeclaration && it.validate() }
            .forEach { generateMapper(it as KSClassDeclaration) }
        return invalidSymbols
    }

    /**
     * Generates a concrete file containing short-named two-way extension mapper mappings
     * for the provided class.
     *
     * @param classDeclaration The mapper class declaration.
     */
    private fun generateMapper(classDeclaration: KSClassDeclaration) {
        val autoMapperInterface = classDeclaration.superTypes
            .map { it.resolve() }
            .find { it.declaration.qualifiedName?.asString() == "feo.health.mapper.IMapper" }
            ?: run {
                logger.warn(
                    "${classDeclaration.simpleName.asString()} must implement IMapper interface",
                    classDeclaration
                )
                return
            }

        val isPrivate = classDeclaration.isPrivate()
        if (!isPrivate)
            throw RuntimeException("${classDeclaration.simpleName.asString()} should be private!")

        val typeArguments = autoMapperInterface.arguments
        if (typeArguments.size != 2) {
            logger.warn(
                "IMapper in ${classDeclaration.simpleName.asString()} must have exactly 2 type arguments",
                classDeclaration
            )
            return
        }

        val firstType = typeArguments[0].type?.resolve()
        val secondType = typeArguments[1].type?.resolve()
        if (firstType == null || secondType == null) {
            logger.warn(
                "Cannot resolve generic types in ${classDeclaration.simpleName.asString()}",
                classDeclaration
            )
            return
        }

        val firstTypeName = firstType.declaration.simpleName.asString()
        val secondTypeName = secondType.declaration.simpleName.asString()
        val firstQualified = firstType.declaration.qualifiedName!!.asString()
        val secondQualified = secondType.declaration.qualifiedName!!.asString()

        val packageName = classDeclaration.packageName.asString()
        val originalMapperName = classDeclaration.simpleName.asString()
        val generatedMapperName = "${firstTypeName}To${secondTypeName}Mapper"
        val fileName = generatedMapperName

        val file = codeGenerator.createNewFile(
            dependencies = Dependencies(true, classDeclaration.containingFile!!),
            packageName = packageName,
            fileName = fileName
        )

        val (toSecondMethodName, toFirstMethodName) = getShortenedMethodNames(firstTypeName, secondTypeName)
        val toSecondList = "${toSecondMethodName}List"
        val toSecondFlow = "${toSecondMethodName}Flow"
        val toSecondFlowList = "${toSecondMethodName}FlowList"

        val toFirstList = "${toFirstMethodName}List"
        val toFirstFlow = "${toFirstMethodName}Flow"
        val toFirstFlowList = "${toFirstMethodName}FlowList"

        val content = """
        package $packageName

        import kotlinx.coroutines.flow.Flow
        import kotlinx.coroutines.flow.map
        import kotlin.collections.List

        object $generatedMapperName {

            private fun getMapperInstance(): Any {
                try {
                    val cls = Class.forName("${classDeclaration.packageName.asString()}.$originalMapperName")
                    return try {
                        cls.getField("INSTANCE").get(null)
                    } catch (e: NoSuchFieldException) {
                        cls.getDeclaredConstructor().let {
                            it.isAccessible = true
                            it.newInstance()
                        }
                    }
                } catch (e: Throwable) {
                    throw RuntimeException("Cannot load mapper class ${classDeclaration.packageName.asString()}.$originalMapperName via reflection", e)
                }
            }

            fun $firstQualified.$toSecondMethodName(): $secondQualified {
                val mapper = getMapperInstance()
                try {
                    val mapperClass = mapper.javaClass
                    val paramClass = Class.forName("$firstQualified")
                    val method = mapperClass.getDeclaredMethod("toSecond", paramClass)
                    method.isAccessible = true
                    @Suppress("UNCHECKED_CAST")
                    return method.invoke(mapper, this) as $secondQualified
                } catch (e: Throwable) {
                    throw RuntimeException("Failed to invoke toSecond on $originalMapperName", e)
                }
            }

            fun $secondQualified.$toFirstMethodName(): $firstQualified {
                val mapper = getMapperInstance()
                try {
                    val mapperClass = mapper.javaClass
                    val paramClass = Class.forName("$secondQualified")
                    val method = mapperClass.getDeclaredMethod("toFirst", paramClass)
                    method.isAccessible = true
                    @Suppress("UNCHECKED_CAST")
                    return method.invoke(mapper, this) as $firstQualified
                } catch (e: Throwable) {
                    throw RuntimeException("Failed to invoke toFirst on $originalMapperName", e)
                }
            }

            fun List<$firstQualified>.$toSecondList(): List<$secondQualified> = 
                this.map { it.$toSecondMethodName() }
            fun List<$secondQualified>.$toFirstList(): List<$firstQualified> = 
                this.map { it.$toFirstMethodName() }
            
            fun Flow<List<$firstQualified>>.$toSecondFlowList(): Flow<List<$secondQualified>> = 
                this.map { it.$toSecondList() }
            fun Flow<List<$secondQualified>>.$toFirstFlowList(): Flow<List<$firstQualified>> = 
                this.map { it.$toFirstList() }
            
            fun Flow<$firstQualified>.$toSecondFlow(): Flow<$secondQualified> = 
                this.map { it.$toSecondMethodName() }
            fun Flow<$secondQualified>.$toFirstFlow(): Flow<$firstQualified> = 
                this.map { it.$toFirstMethodName() }
        }
    """.trimIndent()

        file.use { outputStream ->
            outputStream.write(content.toByteArray())
        }
    }

    /**
     * Deduces short target method names by performing CamelCase word tokenization
     * and subtracting intersection of words between source and target classes.
     *
     * @param first Qualified/simple name of the first class.
     * @param second Qualified/simple name of the second class.
     * @return Pair of short names to map First->Second and Second->First respectively.
     */
    private fun getShortenedMethodNames(first: String, second: String): Pair<String, String> {
        /**
         * Inner helper function calculating token set subtraction.
         *
         * @param source The source class name.
         * @param target The target class name.
         * @return The resulting short name prefix.
         */
        fun getMethodName(source: String, target: String): String {
            val sourceWords = source.split(Regex("(?=[A-Z])")).filter { it.isNotEmpty() }
            val targetWords = target.split(Regex("(?=[A-Z])")).filter { it.isNotEmpty() }
            
            val commonWords = sourceWords.intersect(targetWords.toSet())
            val remainingWords = targetWords.filter { !commonWords.contains(it) }
            
            val result = if (remainingWords.isEmpty()) target else remainingWords.joinToString("")
            return "to$result"
        }
        
        return Pair(getMethodName(first, second), getMethodName(second, first))
    }

    /**
     * Invoked at the completion of processing rounds.
     */
    override fun finish() {}

    /**
     * Invoked if KSP encounters processing round errors.
     */
    override fun onError() {}
}