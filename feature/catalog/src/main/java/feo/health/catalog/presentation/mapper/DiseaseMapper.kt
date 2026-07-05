package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.DiseaseDomain
import feo.health.catalog.presentation.model.Disease
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Mapper object for translating between [Disease] and [DiseaseDomain].
 *
 * This mapper uses the @Mapper annotation and implements [IMapper].
 */
@Mapper
private object DiseaseMapper : IMapper<Disease, DiseaseDomain> {
    /**
     * Converts a [Disease] instance to [DiseaseDomain].
     *
     * @return The converted [DiseaseDomain] instance.
     */
    override fun Disease.toSecond(): DiseaseDomain =
        DiseaseDomain(
            name = name,
            link = link
        )

    /**
     * Converts a [DiseaseDomain] instance back to [Disease].
     *
     * @return The converted [Disease] instance.
     */
    override fun DiseaseDomain.toFirst(): Disease =
        Disease(
            name = name,
            link = link
        )
}