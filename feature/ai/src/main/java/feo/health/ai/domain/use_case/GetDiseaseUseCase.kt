package feo.health.ai.domain.use_case

import feo.health.ai.domain.model.request.FeatureDiseaseRequestDomain
import feo.health.ai.domain.repository.IAiRepository
import javax.inject.Inject

/**
 * Use case to analyze symptom descriptions and retrieve a diagnostic analysis.
 *
 * @property aiRepository AI remote services repository provider.
 */
class GetDiseaseUseCase @Inject constructor(
    private val aiRepository: IAiRepository
) {
    /**
     * Executes the disease diagnostic search request.
     *
     * @param diseaseRequestDomain Symptoms description request details.
     * @return Resolved probable disease diagnostics results.
     */
    suspend operator fun invoke(diseaseRequestDomain: FeatureDiseaseRequestDomain) =
        aiRepository.getDisease(diseaseRequestDomain)
}