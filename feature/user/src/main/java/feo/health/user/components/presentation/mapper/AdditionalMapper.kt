package feo.health.user.components.presentation.mapper

import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.presentation.mapper.CatalogItemDomainToUCatalogItemMapper.toUCatalogItemList

internal object AdditionalMapper {
    fun Map<String, List<CatalogItemDomain>>.toPresentationMap() = this.mapValues {
        it.value.toUCatalogItemList()
    }
}