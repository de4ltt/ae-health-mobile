package feo.health.catalog.domain.use_case.pharmacy

import feo.health.catalog.domain.model.PharmacyDomain
import feo.health.catalog.domain.repository.IPharmacyRepository
import javax.inject.Inject

/**
 * Use case for retrieving a list of pharmacies near a specific location.
 *
 * @property pharmacyRepository The repository used to access pharmacy data.
 */
class GetPharmaciesUseCase @Inject constructor(
    private val pharmacyRepository: IPharmacyRepository
) {
    /**
     * Executes the use case to retrieve pharmacies within a given radius of coordinates.
     *
     * @param lat The latitude coordinate.
     * @param lon The longitude coordinate.
     * @param radius The radius in meters within which pharmacies should be searched.
     * @return A list of [PharmacyDomain] representing the pharmacies in the specified area.
     */
    suspend operator fun invoke(lat: Double, lon: Double, radius: Int): List<PharmacyDomain> =
        pharmacyRepository.getPharmacies(lat, lon, radius)
}