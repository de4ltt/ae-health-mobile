package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.CoordsDomain
import feo.health.catalog.presentation.model.Coords
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Mapper object for translating between [CoordsDomain] and [Coords].
 *
 * This mapper uses the @Mapper annotation and implements [IMapper].
 */
@Mapper
private object CoordsMapper : IMapper<CoordsDomain, Coords> {
    /**
     * Converts a [CoordsDomain] instance to [Coords].
     *
     * @return The converted [Coords] instance.
     */
    override fun CoordsDomain.toSecond(): Coords =
        Coords(lat = lat, lon = lon)

    /**
     * Converts a [Coords] instance back to [CoordsDomain].
     *
     * @return The converted [CoordsDomain] instance.
     */
    override fun Coords.toFirst(): CoordsDomain =
        CoordsDomain(lat = lat, lon = lon)
}