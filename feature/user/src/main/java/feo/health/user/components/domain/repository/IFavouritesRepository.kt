package feo.health.user.components.domain.repository

import feo.health.user.components.domain.model.CatalogItemDomain

/**
 * Repository interface for managing user's favourite catalog items.
 */
interface IFavouritesRepository {
    /**
     * Retrieves the list of user's favourite catalog items grouped by category/type.
     *
     * @return A map where the key is the item type and the value is a list of [CatalogItemDomain] items.
     */
    suspend fun getFavourites(): Map<String, List<CatalogItemDomain>>

    /**
     * Adds a catalog item to the user's favourites.
     *
     * @param catalogItemRequest The catalog item to be added to favourites.
     */
    suspend fun addFavourite(catalogItemRequest: CatalogItemDomain)

    /**
     * Removes a catalog item from the user's favourites.
     *
     * @param catalogItemRequest The catalog item to be removed from favourites.
     */
    suspend fun deleteFavourite(catalogItemRequest: CatalogItemDomain)
}