package feo.health.user.components.data.repository

import feo.health.database.dao.FavouritesDao
import feo.health.network.model.mapResult
import feo.health.user.api.IUserApi
import feo.health.user.components.data.mapper.AdditionalMapper.toDomainMap
import feo.health.user.components.data.mapper.AdditionalMapper.toDto
import feo.health.user.components.data.mapper.AdditionalMapper.toDomain
import feo.health.user.components.data.mapper.AdditionalMapper.toEntity
import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.domain.repository.IFavouritesRepository
import javax.inject.Inject

/**
 * Repository interface implementation managing user bookmarked favourites remote queries.
 * Exposes Room database caching for offline-first resilience.
 *
 * @property favouritesDao Local database transactions interface.
 * @property userApi Remote user action endpoints API client.
 */
class FavouriteRepository @Inject constructor(
    private val favouritesDao: FavouritesDao,
    private val userApi: IUserApi
) : IFavouritesRepository {

    /**
     * Queries user's bookmarked favourites.
     * Falls back to Room database cache if offline.
     *
     * @return Map containing bookmarked domain lists categorized by type key strings.
     */
    override suspend fun getFavourites(): Map<String, List<CatalogItemDomain>> {
        return try {
            val remoteResult = userApi.getFavourites().mapResult { it.toDomainMap() }
            favouritesDao.clearFavourites()
            val entities = remoteResult.flatMap { entry ->
                entry.value.map { domain ->
                    val uniqueId = "${entry.key}_${domain.link ?: domain.name}"
                    domain.toEntity(uniqueId, entry.key)
                }
            }
            favouritesDao.insertFavourites(entities)
            remoteResult
        } catch (e: Exception) {
            val cachedEntities = favouritesDao.getFavouritesList()
            cachedEntities.groupBy({ it.groupKey }, { it.toDomain() })
        }
    }

    /**
     * Bookmarks specified catalog item.
     *
     * @param catalogItemRequest The catalog item details parameters.
     */
    override suspend fun addFavourite(catalogItemRequest: CatalogItemDomain) {
        val uniqueId = "${catalogItemRequest.type}_${catalogItemRequest.link ?: catalogItemRequest.name}"
        favouritesDao.insertFavourites(listOf(catalogItemRequest.toEntity(uniqueId, catalogItemRequest.type)))
        try {
            userApi.addFavourite(catalogItemRequest.toDto()).mapResult { it }
        } catch (e: Exception) {
            // Keep local cache
        }
    }

    /**
     * Unbookmarks specified catalog item.
     *
     * @param catalogItemRequest Target catalog item details parameters.
     */
    override suspend fun deleteFavourite(catalogItemRequest: CatalogItemDomain) {
        val uniqueId = "${catalogItemRequest.type}_${catalogItemRequest.link ?: catalogItemRequest.name}"
        favouritesDao.deleteFavourite(uniqueId)
        try {
            userApi.deleteFavourite(catalogItemRequest.toDto()).mapResult { it }
        } catch (e: Exception) {
            // Keep local deletion
        }
    }
}