package feo.health.catalog.domain.use_case.disease

import feo.health.catalog.domain.model.DiseaseDomain
import feo.health.catalog.domain.repository.IDiseaseRepository
import javax.inject.Inject

/**
 * Use case for retrieving a list of diseases, optionally filtered by a search query.
 *
 * @property diseaseRepository The repository used to access disease data.
 */
class GetDiseasesUseCase @Inject constructor(
    private val diseaseRepository: IDiseaseRepository
) {
    /**
     * Executes the use case to search or retrieve diseases.
     *
     * @param q The search query string to filter diseases.
     * @return A list of [DiseaseDomain] matching the criteria.
     */
    suspend operator fun invoke(q: String): List<DiseaseDomain> =
        diseaseRepository.getDiseases(q)
}