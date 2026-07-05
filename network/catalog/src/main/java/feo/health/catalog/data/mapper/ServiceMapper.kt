package feo.health.catalog.data.mapper

import feo.health.catalog.domain.model.ServiceDomain
import feo.health.catalog.services.dto.ServiceDto
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between serialization [ServiceDto] and domain [ServiceDomain] models.
 */
@Mapper
private object ServiceMapper : IMapper<ServiceDto, ServiceDomain> {
    /**
     * Converts a [ServiceDto] serial model to its corresponding domain [ServiceDomain] entity.
     *
     * @return Resolved [ServiceDomain].
     */
    override fun ServiceDto.toSecond(): ServiceDomain =
        ServiceDomain(
            name = name,
            link = link,
            itemType = itemType
        )

    /**
     * Converts a [ServiceDomain] domain entity to its corresponding serial [ServiceDto] model.
     *
     * @return Resolved [ServiceDto].
     */
    override fun ServiceDomain.toFirst(): ServiceDto =
        ServiceDto(
            name = name,
            link = link,
            itemType = itemType
        )
}