package feo.health.user.components.domain.use_case.history

import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.domain.repository.IHistoryRepository
import javax.inject.Inject

class DeleteHistoryItemUseCase @Inject constructor(
    private val historyRepository: IHistoryRepository
) {
    suspend operator fun invoke(catalogItemDomain: CatalogItemDomain) =
        historyRepository.deleteHistoryItem(catalogItemDomain)
}