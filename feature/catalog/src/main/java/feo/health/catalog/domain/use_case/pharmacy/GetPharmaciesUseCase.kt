package feo.health.catalog.domain.use_case.pharmacy

import feo.health.catalog.domain.model.PharmacyDomain
import feo.health.catalog.domain.repository.IPharmacyRepository
import javax.inject.Inject

class GetPharmaciesUseCase @Inject constructor(
    private val pharmacyRepository: IPharmacyRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double, radius: Int): List<PharmacyDomain> =
        pharmacyRepository.getPharmacies(lat, lon, radius)
}