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

            fun $firstQualified.to$secondTypeName(): $secondQualified {
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

            fun $secondQualified.to$firstTypeName(): $firstQualified {
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

            fun List<$firstQualified>.to${secondTypeName}List(): List<$secondQualified> = 
                this.map { it.to$secondTypeName() }
            fun List<$secondQualified>.to${firstTypeName}List(): List<$firstQualified> = 
                this.map { it.to$firstTypeName() }
            
            fun Flow<List<$firstQualified>>.to${secondTypeName}FlowList(): Flow<List<$secondQualified>> = 
                this.map { it.to${secondTypeName}List() }
            fun Flow<List<$secondQualified>>.to${firstTypeName}FlowList(): Flow<List<$firstQualified>> = 
                this.map { it.to${firstTypeName}List() }
            
            fun Flow<$firstQualified>.to${secondTypeName}Flow(): Flow<$secondQualified> = 
                this.map { it.to$secondTypeName() }
            fun Flow<$secondQualified>.to${firstTypeName}Flow(): Flow<$firstQualified> = 
                this.map { it.to$firstTypeName() }
        }
    """.trimIndent()

        file.use { outputStream ->
            outputStream.write(content.toByteArray())
        }
    }

    override fun finish() {}
    override fun onError() {}
}