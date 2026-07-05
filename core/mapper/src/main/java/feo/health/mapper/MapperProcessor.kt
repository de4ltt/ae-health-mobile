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

class MapperProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val annotationName = "feo.health.mapper.Mapper"
        val symbols = resolver.getSymbolsWithAnnotation(annotationName)
        val invalidSymbols = symbols.filter { !it.validate() }.toList()
        symbols.filter { it is KSClassDeclaration && it.validate() }
            .forEach { generateMapper(it as KSClassDeclaration) }
        return invalidSymbols
    }

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

        val longToSecond = "to$secondTypeName"
        val longToFirst = "to$firstTypeName"

        val deprecationToSecond = if (toSecondMethodName != longToSecond) {
            """
            @Deprecated("Use $toSecondMethodName instead", ReplaceWith("$toSecondMethodName()"))
            fun $firstQualified.$longToSecond(): $secondQualified = this.$toSecondMethodName()

            @Deprecated("Use $toSecondList instead", ReplaceWith("$toSecondList()"))
            fun List<$firstQualified>.${longToSecond}List(): List<$secondQualified> = this.$toSecondList()

            @Deprecated("Use $toSecondFlowList instead", ReplaceWith("$toSecondFlowList()"))
            fun Flow<List<$firstQualified>>.${longToSecond}FlowList(): Flow<List<$secondQualified>> = this.$toSecondFlowList()

            @Deprecated("Use $toSecondFlow instead", ReplaceWith("$toSecondFlow()"))
            fun Flow<$firstQualified>.${longToSecond}Flow(): Flow<$secondQualified> = this.$toSecondFlow()
            """.trimIndent()
        } else ""

        val deprecationToFirst = if (toFirstMethodName != longToFirst) {
            """
            @Deprecated("Use $toFirstMethodName instead", ReplaceWith("$toFirstMethodName()"))
            fun $secondQualified.$longToFirst(): $firstQualified = this.$toFirstMethodName()

            @Deprecated("Use $toFirstList instead", ReplaceWith("$toFirstList()"))
            fun List<$secondQualified>.${longToFirst}List(): List<$firstQualified> = this.$toFirstList()

            @Deprecated("Use $toFirstFlowList instead", ReplaceWith("$toFirstFlowList()"))
            fun Flow<List<$secondQualified>>.${longToFirst}FlowList(): Flow<List<$firstQualified>> = this.$toFirstFlowList()

            @Deprecated("Use $toFirstFlow instead", ReplaceWith("$toFirstFlow()"))
            fun Flow<$secondQualified>.${longToFirst}Flow(): Flow<$firstQualified> = this.$toFirstFlow()
            """.trimIndent()
        } else ""

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

            $deprecationToSecond

            $deprecationToFirst
        }
    """.trimIndent()

        file.use { outputStream ->
            outputStream.write(content.toByteArray())
        }
    }

    private fun getShortenedMethodNames(first: String, second: String): Pair<String, String> {
        val suffixes = listOf(
            "RequestDomain", "ResponseDomain", "Request", "Response", 
            "Domain", "Network", "DTO", "Dto", "Entity", "Model"
        )
        
        fun getMethodName(source: String, target: String): String {
            val sourceWords = source.split(Regex("(?=[A-Z])")).filter { it.isNotEmpty() }
            val targetWords = target.split(Regex("(?=[A-Z])")).filter { it.isNotEmpty() }
            
            val commonWords = sourceWords.intersect(targetWords.toSet())
            if (commonWords.isNotEmpty()) {
                val matchedSuffix = suffixes.find { target.endsWith(it) }
                if (matchedSuffix != null) {
                    return "to$matchedSuffix"
                }
            }
            
            val noiseWords = setOf("Domain", "Network", "DTO", "Dto", "Entity", "Model", "Feature")
            val filteredWords = targetWords.filter { !noiseWords.contains(it) }
            val result = if (filteredWords.isEmpty()) target else filteredWords.joinToString("")
            return "to$result"
        }
        
        return Pair(getMethodName(first, second), getMethodName(second, first))
    }

    override fun finish() {}
    override fun onError() {}
}