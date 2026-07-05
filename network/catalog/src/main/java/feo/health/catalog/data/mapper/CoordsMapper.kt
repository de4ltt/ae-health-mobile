package feo.health.catalog.data.mapper

import feo.health.catalog.domain.model.CoordsDomain
import feo.health.catalog.search.dto.CoordsDto
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object CoordsMapper: IMapper<CoordsDomain, CoordsDto> {
    override fun CoordsDomain.toSecond(): CoordsDto =
        CoordsDto(lat = lat, lon = lon)
    override fun CoordsDto.toFirst(): CoordsDomain =
        CoordsDomain(lat = lat, lon = lon)
}