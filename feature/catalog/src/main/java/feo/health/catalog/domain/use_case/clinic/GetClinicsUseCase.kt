package feo.health.catalog.domain.use_case.clinic

import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.domain.repository.IClinicRepository
import javax.inject.Inject

/**
 * Use case for retrieving a list of clinics, optionally filtered by a search query.
 *
 * @property clinicRepository The repository used to access clinic data.
 */
class GetClinicsUseCase @Inject constructor(
    private val clinicRepository: IClinicRepository
) {
    /**
     * Executes the use case to search or retrieve clinics.
     *
     * @param q The search query string to filter clinics.
     * @param isLocated A flag indicating whether the clinics should be filtered by location/geocoding status.
     * @return A list of [ClinicDomain] matching the criteria.
     */
    suspend operator fun invoke(q: String, isLocated: Boolean): List<ClinicDomain> =
        clinicRepository.getClinics(q, isLocated)
}

