package feo.health.user.components.presentation.mapper

import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.presentation.mapper.CatalogItemDomainToUCatalogItemMapper.toUList

/**
 * Mapper helper object for converting domain models to presentation models
 * specifically for collections and maps.
 */
internal object AdditionalMapper {
    /**
     * Converts a map of domain catalog items to a map of presentation catalog items.
     *
     * @return A map with the same keys, but containing lists of presentation models [UCatalogItem] as values.
     */
    fun Map<String, List<CatalogItemDomain>>.toPresentationMap() = this.mapValues {
        it.value.toUList()
    }
}