package feo.health.catalog.domain.use_case.pharmacy

import feo.health.catalog.domain.model.PharmacyDomain
import feo.health.catalog.domain.repository.IPharmacyRepository
import javax.inject.Inject

class GetPharmacyByIdUseCase @Inject constructor(
    private val pharmacyRepository: IPharmacyRepository
) {
    suspend operator fun invoke(id: Long): PharmacyDomain =
        pharmacyRepository.getPharmacyById(id)
}