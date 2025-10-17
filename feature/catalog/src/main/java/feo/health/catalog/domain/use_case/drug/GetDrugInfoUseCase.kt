package feo.health.catalog.domain.use_case.drug

import feo.health.catalog.domain.model.DrugDomain
import feo.health.catalog.domain.repository.IDrugRepository
import javax.inject.Inject

class GetDrugInfoUseCase @Inject constructor(
    private val drugRepository: IDrugRepository
) {
    suspend operator fun invoke(link: String): DrugDomain =
        drugRepository.getDrugInfo(link)
}