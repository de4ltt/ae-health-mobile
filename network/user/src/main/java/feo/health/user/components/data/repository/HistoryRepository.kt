package feo.health.user.components.data.repository

import feo.health.network.model.mapResult
import feo.health.user.api.IUserApi
import feo.health.user.components.data.mapper.AdditionalMapper.toDomainMap
import feo.health.user.components.data.mapper.AdditionalMapper.toDto
import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.domain.repository.IHistoryRepository
import javax.inject.Inject

/**
 * Repository interface implementation managing user interaction history feeds queries.
 *
 * @property userApi Remote user action endpoints API client.
 */
class HistoryRepository @Inject constructor(
    private val userApi: IUserApi
): IHistoryRepository {

    /**
     * Queries user's interaction logs history list.
     *
     * @return Map containing history domain lists categorized by date key strings.
     */
    override suspend fun getHistory(): Map<String, List<CatalogItemDomain>> =
        userApi.getHistory().mapResult { it.toDomainMap() }

    /**
     * Deletes single history item timeline entry.
     *
     * @param catalogItem Target history item domain entity.
     */
    override suspend fun deleteHistoryItem(catalogItem: CatalogItemDomain) =
        userApi.deleteHistoryItem(catalogItem.toDto()).mapResult { it }
}