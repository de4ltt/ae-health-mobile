package feo.health.user.components.domain.use_case.util

import feo.health.user.components.domain.use_case.history.DeleteHistoryItemUseCase
import feo.health.user.components.domain.use_case.history.GetHistoryUseCase

interface IHistoryUseCases {
    val getHistoryUseCase: GetHistoryUseCase
    val deleteHistoryItemUseCase: DeleteHistoryItemUseCase
}