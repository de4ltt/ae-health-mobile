package feo.health.user.components.domain.repository

import feo.health.user.components.domain.model.CatalogItemDomain

interface IHistoryRepository {
    suspend fun getHistory(): Map<String, List<CatalogItemDomain>>
    suspend fun deleteHistoryItem(catalogItem: CatalogItemDomain)
}