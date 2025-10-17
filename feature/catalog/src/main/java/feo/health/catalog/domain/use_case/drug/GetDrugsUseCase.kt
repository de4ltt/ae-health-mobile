package feo.health.catalog.domain.use_case.drug

import feo.health.catalog.domain.model.DrugDomain
import feo.health.catalog.domain.repository.IDrugRepository
import javax.inject.Inject

class GetDrugsUseCase @Inject constructor(
    private val drugRepository: IDrugRepository
) {
    suspend operator fun invoke(q: String): List<DrugDomain> =
        drugRepository.getDrugs(q)
}