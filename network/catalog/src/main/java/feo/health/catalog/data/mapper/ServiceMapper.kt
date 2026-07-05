package feo.health.catalog.data.mapper

import feo.health.catalog.domain.model.ServiceDomain
import feo.health.catalog.services.dto.ServiceDto
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object ServiceMapper : IMapper<ServiceDto, ServiceDomain> {
    override fun ServiceDto.toSecond(): ServiceDomain =
        ServiceDomain(
            name = name,
            link = link,
            itemType = itemType
        )

    override fun ServiceDomain.toFirst(): ServiceDto =
        ServiceDto(
            name = name,
            link = link,
            itemType = itemType
        )
}