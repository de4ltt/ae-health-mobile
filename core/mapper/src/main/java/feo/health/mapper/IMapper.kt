package feo.health.mapper

/**
 * Interface contract representing a two-way mapper implementation.
 *
 * Mappers implementing this interface and annotated with [@Mapper] will have extension
 * mapping methods automatically generated via KSP processor ([MapperProcessor]).
 *
 * @param First The source/first data model type (e.g. DTO).
 * @param Second The target/second data model type (e.g. Domain model).
 */
interface IMapper<First, Second> {
    /**
     * Maps an instance of type [First] to type [Second].
     */
    fun First.toSecond(): Second

    /**
     * Maps an instance of type [Second] to type [First].
     */
    fun Second.toFirst(): First
}