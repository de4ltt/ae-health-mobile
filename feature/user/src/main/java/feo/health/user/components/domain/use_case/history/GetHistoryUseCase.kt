package feo.health.user.components.domain.use_case.history

import feo.health.user.components.domain.repository.IHistoryRepository
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val historyRepository: IHistoryRepository
){
    suspend operator fun invoke() = historyRepository.getHistory()
}