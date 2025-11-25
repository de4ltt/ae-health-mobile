package feo.health.user.components.data.repository

import feo.health.network.model.mapResult
import feo.health.user.api.IUserApi
import feo.health.user.components.data.mapper.AdditionalMapper.toDomainMap
import feo.health.user.components.data.mapper.AdditionalMapper.toDto
import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.domain.repository.IFavouritesRepository
import javax.inject.Inject

class FavouriteRepository @Inject constructor(
    private val userApi: IUserApi
) : IFavouritesRepository {
    override suspend fun getFavourites(): Map<String, List<CatalogItemDomain>> =
        userApi.getFavourites().mapResult { it.toDomainMap() }

    override suspend fun addFavourite(catalogItemRequest: CatalogItemDomain) =
        userApi.addFavourite(catalogItemRequest.toDto()).mapResult { it }

    override suspend fun deleteFavourite(catalogItemRequest: CatalogItemDomain) =
        userApi.deleteFavourite(catalogItemRequest.toDto()).mapResult { it }
}