package feo.health.user.components.domain.use_case.util

import feo.health.user.components.domain.use_case.history.DeleteHistoryItemUseCase
import feo.health.user.components.domain.use_case.history.GetHistoryUseCase

/**
 * Interface representing the wrapper/container for all history-related use cases.
 */
interface IHistoryUseCases {
    /**
     * Use case for retrieving the user's history catalog items.
     */
    val getHistoryUseCase: GetHistoryUseCase

    /**
     * Use case for deleting a catalog item from history.
     */
    val deleteHistoryItemUseCase: DeleteHistoryItemUseCase
}