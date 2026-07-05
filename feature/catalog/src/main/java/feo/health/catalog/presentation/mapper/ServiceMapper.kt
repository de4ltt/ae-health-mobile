package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.ServiceDomain
import feo.health.catalog.presentation.model.ICatalog
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Mapper object for translating between [ICatalog.Service] and [ServiceDomain].
 *
 * This mapper uses the @Mapper annotation and implements [IMapper].
 */
@Mapper
private object ServiceMapper : IMapper<ICatalog.Service, ServiceDomain> {
    /**
     * Converts an [ICatalog.Service] instance to [ServiceDomain].
     *
     * @return The converted [ServiceDomain] instance.
     */
    override fun ICatalog.Service.toSecond(): ServiceDomain =
        ServiceDomain(
            name = name,
            link = link,
            itemType = itemType
        )

    /**
     * Converts a [ServiceDomain] instance back to [ICatalog.Service].
     *
     * @return The converted [ICatalog.Service] instance.
     */
    override fun ServiceDomain.toFirst(): ICatalog.Service =
        ICatalog.Service(
            name = name,
            link = link,
            itemType = itemType
        )
}