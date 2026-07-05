package feo.health.user.components.data.repository

import feo.health.network.model.mapResult
import feo.health.user.api.IUserApi
import feo.health.user.components.data.mapper.AdditionalMapper.toDomainMap
import feo.health.user.components.data.mapper.AdditionalMapper.toDto
import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.domain.repository.IFavouritesRepository
import javax.inject.Inject

/**
 * Repository interface implementation managing user bookmarked favourites remote queries.
 *
 * @property userApi Remote user action endpoints API client.
 */
class FavouriteRepository @Inject constructor(
    private val userApi: IUserApi
) : IFavouritesRepository {

    /**
     * Queries user's bookmarked favourites.
     *
     * @return Map containing bookmarked domain lists categorized by type key strings.
     */
    override suspend fun getFavourites(): Map<String, List<CatalogItemDomain>> =
        userApi.getFavourites().mapResult { it.toDomainMap() }

    /**
     * Bookmarks specified catalog item.
     *
     * @param catalogItemRequest The catalog item details parameters.
     */
    override suspend fun addFavourite(catalogItemRequest: CatalogItemDomain) =
        userApi.addFavourite(catalogItemRequest.toDto()).mapResult { it }

    /**
     * Unbookmarks specified catalog item.
     *
     * @param catalogItemRequest Target catalog item details parameters.
     */
    override suspend fun deleteFavourite(catalogItemRequest: CatalogItemDomain) =
        userApi.deleteFavourite(catalogItemRequest.toDto()).mapResult { it }
}