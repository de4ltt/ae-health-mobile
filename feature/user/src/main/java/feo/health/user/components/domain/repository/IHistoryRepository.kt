package feo.health.user.components.domain.repository

import feo.health.user.components.domain.model.CatalogItemDomain

/**
 * Repository interface for managing user's history catalog items.
 */
interface IHistoryRepository {
    /**
     * Retrieves the list of user's history catalog items grouped by category/type.
     *
     * @return A map where the key is the item type and the value is a list of [CatalogItemDomain] items.
     */
    suspend fun getHistory(): Map<String, List<CatalogItemDomain>>

    /**
     * Deletes a catalog item from the user's history.
     *
     * @param catalogItem The catalog item to be removed from history.
     */
    suspend fun deleteHistoryItem(catalogItem: CatalogItemDomain)
}