package feo.health.ai.domain.use_case

import feo.health.ai.data.repository.AiRepository
import feo.health.ai.domain.model.request.FeatureProcedureRequestDomain
import javax.inject.Inject

class GetProcedureInfoUseCase @Inject constructor(
    private val aiRepository: AiRepository
) {
    suspend operator fun invoke(procedureRequestDomain: FeatureProcedureRequestDomain) =
        aiRepository.getProcedureInfo(procedureRequestDomain)
}