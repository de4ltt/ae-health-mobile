package feo.health.user.components.presentation.mapper

import feo.health.user.components.data.mapper.CatalogItemDomainToCatalogItemResponseMapper.toCatalogItemDomainList
import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.presentation.mapper.CatalogItemDomainToUCatalogItemMapper.toUCatalogItemList
import feo.health.user.dto.request.CatalogItemRequest
import feo.health.user.dto.response.CatalogItemResponse

internal object AdditionalMapper {
    fun Map<String, List<CatalogItemDomain>>.toPresentationMap() = this.mapValues {
        it.value.toUCatalogItemList()
    }
}