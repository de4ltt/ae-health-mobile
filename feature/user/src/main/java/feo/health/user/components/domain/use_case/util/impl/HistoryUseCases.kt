package feo.health.user.components.domain.use_case.util.impl

import feo.health.user.components.domain.use_case.history.DeleteHistoryItemUseCase
import feo.health.user.components.domain.use_case.history.GetHistoryUseCase
import feo.health.user.components.domain.use_case.util.IHistoryUseCases
import javax.inject.Inject

data class HistoryUseCases @Inject constructor(
    override val getHistoryUseCase: GetHistoryUseCase,
    override val deleteHistoryItemUseCase: DeleteHistoryItemUseCase
): IHistoryUseCases
