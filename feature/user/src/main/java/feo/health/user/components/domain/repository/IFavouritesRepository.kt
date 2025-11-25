package feo.health.user.components.domain.repository

import feo.health.user.components.domain.model.CatalogItemDomain

interface IFavouritesRepository {
    suspend fun getFavourites(): Map<String, List<CatalogItemDomain>>
    suspend fun addFavourite(catalogItemRequest: CatalogItemDomain)
    suspend fun deleteFavourite(catalogItemRequest: CatalogItemDomain)
}