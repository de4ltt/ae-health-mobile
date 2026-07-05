package feo.health.user.components.data.mapper

import feo.health.user.components.data.mapper.CatalogItemDomainToCatalogItemResponseMapper.toDomainList
import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.dto.request.CatalogItemRequest
import feo.health.user.dto.response.CatalogItemResponse

/**
 * Helper mapper extensions handling collection-level user data mapping structures.
 */
internal object AdditionalMapper {
    /**
     * Converts a map of catalog item lists to their domain models representation.
     *
     * @return Resolved map containing domain lists.
     */
    fun Map<String, List<CatalogItemResponse>>.toDomainMap() =
        this.mapValues { value -> value.value.toDomainList() }

    /**
     * Converts a [CatalogItemDomain] domain entity to its corresponding serial [CatalogItemRequest] model.
     *
     * @return Resolved [CatalogItemRequest].
     */
    fun CatalogItemDomain.toDto() = CatalogItemRequest(
        type = type,
        link = link
    )
}