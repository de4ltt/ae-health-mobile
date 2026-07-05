package feo.health.mapper

/**
 * Annotation used to mark private mapper objects implementing [IMapper].
 *
 * The KSP processor ([MapperProcessor]) scans for classes annotated with [@Mapper]
 * and generates type-safe, shortened extension functions for object/list mapping.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Mapper
