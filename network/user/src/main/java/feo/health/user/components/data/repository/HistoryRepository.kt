package feo.health.user.components.data.repository

import feo.health.database.dao.HistoryDao
import feo.health.network.model.mapResult
import feo.health.user.api.IUserApi
import feo.health.user.components.data.mapper.AdditionalMapper.toDomainMap
import feo.health.user.components.data.mapper.AdditionalMapper.toDto
import feo.health.user.components.data.mapper.AdditionalMapper.toDomain
import feo.health.user.components.data.mapper.AdditionalMapper.toEntityHistory
import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.domain.repository.IHistoryRepository
import javax.inject.Inject

/**
 * Repository interface implementation managing user interaction history feeds queries.
 * Exposes Room database caching for offline-first resilience.
 *
 * @property historyDao Local database transactions interface.
 * @property userApi Remote user action endpoints API client.
 */
class HistoryRepository @Inject constructor(
    private val historyDao: HistoryDao,
    private val userApi: IUserApi
): IHistoryRepository {

    /**
     * Queries user's interaction logs history list.
     * Falls back to Room database cache if offline.
     *
     * @return Map containing history domain lists categorized by date key strings.
     */
    override suspend fun getHistory(): Map<String, List<CatalogItemDomain>> {
        return try {
            val remoteResult = userApi.getHistory().mapResult { it.toDomainMap() }
            historyDao.clearHistory()
            val entities = remoteResult.flatMap { entry ->
                entry.value.map { domain ->
                    val uniqueId = "${entry.key}_${domain.link ?: domain.name}"
                    domain.toEntityHistory(uniqueId, entry.key)
                }
            }
            historyDao.insertHistory(entities)
            remoteResult
        } catch (e: Exception) {
            val cachedEntities = historyDao.getHistoryList()
            cachedEntities.groupBy({ it.groupKey }, { it.toDomain() })
        }
    }

    /**
     * Deletes single history item timeline entry.
     *
     * @param catalogItem Target history item domain entity.
     */
    override suspend fun deleteHistoryItem(catalogItem: CatalogItemDomain) {
        historyDao.deleteHistoryItemByLinkOrName(catalogItem.link, catalogItem.name)
        try {
            userApi.deleteHistoryItem(catalogItem.toDto()).mapResult { it }
        } catch (e: Exception) {
            // Keep local deletion
        }
    }
}