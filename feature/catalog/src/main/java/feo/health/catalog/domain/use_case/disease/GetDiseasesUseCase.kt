package feo.health.catalog.domain.use_case.disease

import feo.health.catalog.domain.model.DiseaseDomain
import feo.health.catalog.domain.repository.IDiseaseRepository
import javax.inject.Inject

class GetDiseasesUseCase @Inject constructor(
    private val diseaseRepository: IDiseaseRepository
) {
    suspend operator fun invoke(q: String): List<DiseaseDomain> =
        diseaseRepository.getDiseases(q)
}