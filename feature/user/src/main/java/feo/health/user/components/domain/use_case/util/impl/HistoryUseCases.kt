package feo.health.user.components.domain.use_case.util.impl

import feo.health.user.components.domain.use_case.history.DeleteHistoryItemUseCase
import feo.health.user.components.domain.use_case.history.GetHistoryUseCase
import feo.health.user.components.domain.use_case.util.IHistoryUseCases
import javax.inject.Inject

/**
 * Implementation of [IHistoryUseCases] containing all history-related use cases.
 *
 * @property getHistoryUseCase Use case for retrieving the user's history catalog items.
 * @property deleteHistoryItemUseCase Use case for deleting a catalog item from history.
 */
data class HistoryUseCases @Inject constructor(
    override val getHistoryUseCase: GetHistoryUseCase,
    override val deleteHistoryItemUseCase: DeleteHistoryItemUseCase
): IHistoryUseCases
