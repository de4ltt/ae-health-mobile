package feo.health.catalog.domain.use_case.disease

import feo.health.catalog.domain.repository.IDiseaseRepository
import javax.inject.Inject

class GetDiseaseInfoUseCase @Inject constructor(
    private val diseaseRepository: IDiseaseRepository
) {
    suspend operator fun invoke(link: String): String =
        diseaseRepository.getDiseaseInfo(link)
}