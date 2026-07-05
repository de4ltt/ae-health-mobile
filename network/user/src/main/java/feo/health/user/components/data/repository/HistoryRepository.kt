package feo.health.user.components.data.repository

import feo.health.network.model.mapResult
import feo.health.user.api.IUserApi
import feo.health.user.components.data.mapper.AdditionalMapper.toDomainMap
import feo.health.user.components.data.mapper.AdditionalMapper.toDto
import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.domain.repository.IHistoryRepository
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    private val userApi: IUserApi
): IHistoryRepository {
    override suspend fun getHistory(): Map<String, List<CatalogItemDomain>> =
        userApi.getHistory().mapResult { it.toDomainMap() }

    override suspend fun deleteHistoryItem(catalogItem: CatalogItemDomain) =
        userApi.deleteHistoryItem(catalogItem.toDto()).mapResult { it }
}