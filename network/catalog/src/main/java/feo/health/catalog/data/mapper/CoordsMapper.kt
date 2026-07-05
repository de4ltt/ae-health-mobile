package feo.health.catalog.data.mapper

import feo.health.catalog.domain.model.CoordsDomain
import feo.health.catalog.search.dto.CoordsDto
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between serialization [CoordsDto] and domain [CoordsDomain] models.
 */
@Mapper
private object CoordsMapper: IMapper<CoordsDomain, CoordsDto> {
    /**
     * Converts a [CoordsDomain] domain entity to its corresponding serial [CoordsDto] model.
     *
     * @return Resolved [CoordsDto].
     */
    override fun CoordsDomain.toSecond(): CoordsDto =
        CoordsDto(lat = lat, lon = lon)

    /**
     * Converts a [CoordsDto] serial model to its corresponding domain [CoordsDomain] entity.
     *
     * @return Resolved [CoordsDomain].
     */
    override fun CoordsDto.toFirst(): CoordsDomain =
        CoordsDomain(lat = lat, lon = lon)
}