package feo.health.user.components.domain.use_case.history

import feo.health.user.components.domain.repository.IHistoryRepository
import javax.inject.Inject

/**
 * Use case for retrieving the user's history catalog items.
 *
 * @property historyRepository The repository responsible for managing user's history data.
 */
class GetHistoryUseCase @Inject constructor(
    private val historyRepository: IHistoryRepository
){
    /**
     * Executes the use case to retrieve the history list.
     *
     * @return A map of categories to list of history catalog items.
     */
    suspend operator fun invoke() = historyRepository.getHistory()
}