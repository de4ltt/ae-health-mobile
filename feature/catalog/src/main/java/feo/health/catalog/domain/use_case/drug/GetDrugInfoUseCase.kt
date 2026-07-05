package feo.health.catalog.domain.use_case.drug

import feo.health.catalog.domain.model.DrugDomain
import feo.health.catalog.domain.repository.IDrugRepository
import javax.inject.Inject

/**
 * Use case for retrieving detailed information about a specific drug.
 *
 * @property drugRepository The repository used to access drug data.
 */
class GetDrugInfoUseCase @Inject constructor(
    private val drugRepository: IDrugRepository
) {
    /**
     * Executes the use case to retrieve drug details.
     *
     * @param link The unique link or identifier of the drug.
     * @return The [DrugDomain] containing detailed drug information.
     */
    suspend operator fun invoke(link: String): DrugDomain =
        drugRepository.getDrugInfo(link)
}