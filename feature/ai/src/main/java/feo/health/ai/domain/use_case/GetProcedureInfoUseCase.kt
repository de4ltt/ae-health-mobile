package feo.health.ai.domain.use_case

import feo.health.ai.domain.model.request.FeatureProcedureRequestDomain
import feo.health.ai.domain.repository.IAiRepository
import javax.inject.Inject

/**
 * Use case to fetch detailed description and properties about a medical service procedure.
 *
 * @property aiRepository AI remote services repository provider.
 */
class GetProcedureInfoUseCase @Inject constructor(
    private val aiRepository: IAiRepository
) {
    /**
     * Executes the medical service procedure details search request.
     *
     * @param procedureRequestDomain Medical procedure name request details.
     * @return Resolved detailed procedure properties results.
     */
    suspend operator fun invoke(procedureRequestDomain: FeatureProcedureRequestDomain) =
        aiRepository.getProcedureInfo(procedureRequestDomain)
}