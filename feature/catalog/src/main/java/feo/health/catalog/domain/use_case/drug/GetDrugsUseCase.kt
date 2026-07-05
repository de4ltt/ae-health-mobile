package feo.health.catalog.domain.use_case.drug

import feo.health.catalog.domain.model.DrugDomain
import feo.health.catalog.domain.repository.IDrugRepository
import javax.inject.Inject

/**
 * Use case for retrieving a list of drugs, optionally filtered by a search query.
 *
 * @property drugRepository The repository used to access drug data.
 */
class GetDrugsUseCase @Inject constructor(
    private val drugRepository: IDrugRepository
) {
    /**
     * Executes the use case to search or retrieve drugs.
     *
     * @param q The search query string to filter drugs.
     * @return A list of [DrugDomain] matching the criteria.
     */
    suspend operator fun invoke(q: String): List<DrugDomain> =
        drugRepository.getDrugs(q)
}