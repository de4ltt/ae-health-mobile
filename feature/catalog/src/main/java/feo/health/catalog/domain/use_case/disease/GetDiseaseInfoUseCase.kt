package feo.health.catalog.domain.use_case.disease

import feo.health.catalog.domain.repository.IDiseaseRepository
import javax.inject.Inject

/**
 * Use case for retrieving detailed information/description of a specific disease.
 *
 * @property diseaseRepository The repository used to access disease data.
 */
class GetDiseaseInfoUseCase @Inject constructor(
    private val diseaseRepository: IDiseaseRepository
) {
    /**
     * Executes the use case to retrieve disease details.
     *
     * @param link The unique link or identifier of the disease.
     * @return A [String] containing detailed information about the disease.
     */
    suspend operator fun invoke(link: String): String =
        diseaseRepository.getDiseaseInfo(link)
}