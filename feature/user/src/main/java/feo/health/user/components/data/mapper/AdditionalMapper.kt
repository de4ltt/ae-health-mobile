package feo.health.user.components.data.mapper

import feo.health.user.components.data.mapper.CatalogItemDomainToCatalogItemResponseMapper.toCatalogItemDomainList
import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.dto.request.CatalogItemRequest
import feo.health.user.dto.response.CatalogItemResponse

internal object AdditionalMapper {
    fun Map<String, List<CatalogItemResponse>>.toDomainMap() =
        this.mapValues { value -> value.value.toCatalogItemDomainList() }

    fun CatalogItemDomain.toDto() = CatalogItemRequest(
        type = type,
        link = link
    )
}