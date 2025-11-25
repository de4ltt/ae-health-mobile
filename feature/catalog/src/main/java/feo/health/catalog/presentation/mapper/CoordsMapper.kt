package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.CoordsDomain
import feo.health.catalog.presentation.model.Coords
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object CoordsMapper : IMapper<CoordsDomain, Coords> {
    override fun CoordsDomain.toSecond(): Coords =
        Coords(lat = lat, lon = lon)
    override fun Coords.toFirst(): CoordsDomain =
        CoordsDomain(lat = lat, lon = lon)
}