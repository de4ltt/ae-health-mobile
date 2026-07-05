package feo.health.user.components.domain.use_case.history

import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.domain.repository.IHistoryRepository
import javax.inject.Inject

/**
 * Use case for deleting a catalog item from the user's history records.
 *
 * @property historyRepository The repository responsible for managing user's history data.
 */
class DeleteHistoryItemUseCase @Inject constructor(
    private val historyRepository: IHistoryRepository
) {
    /**
     * Executes the use case to delete an item from history.
     *
     * @param catalogItemDomain The catalog item to be deleted from history.
     */
    suspend operator fun invoke(catalogItemDomain: CatalogItemDomain) =
        historyRepository.deleteHistoryItem(catalogItemDomain)
}