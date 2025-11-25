package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.ServiceDomain
import feo.health.catalog.presentation.model.ICatalog
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object ServiceMapper : IMapper<ICatalog.Service, ServiceDomain> {
    override fun ICatalog.Service.toSecond(): ServiceDomain =
        ServiceDomain(
            name = name,
            link = link,
            itemType = itemType
        )

    override fun ServiceDomain.toFirst(): ICatalog.Service =
        ICatalog.Service(
            name = name,
            link = link,
            itemType = itemType
        )
}