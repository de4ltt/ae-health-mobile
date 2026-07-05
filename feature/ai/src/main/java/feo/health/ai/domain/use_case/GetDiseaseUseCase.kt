package feo.health.ai.domain.use_case

import feo.health.ai.domain.repository.IAiRepository
import feo.health.ai.domain.model.request.FeatureDiseaseRequestDomain
import javax.inject.Inject

class GetDiseaseUseCase @Inject constructor(
    private val aiRepository: IAiRepository
) {
    suspend operator fun invoke(diseaseRequestDomain: FeatureDiseaseRequestDomain) =
        aiRepository.getDisease(diseaseRequestDomain)
}