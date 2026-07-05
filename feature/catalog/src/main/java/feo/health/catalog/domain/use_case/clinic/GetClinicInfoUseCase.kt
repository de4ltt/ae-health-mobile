package feo.health.catalog.domain.use_case.clinic

import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.domain.repository.IClinicRepository
import javax.inject.Inject

/**
 * Use case for retrieving detailed information about a specific clinic.
 *
 * @property clinicRepository The repository used to access clinic data.
 */
class GetClinicInfoUseCase @Inject constructor(
    private val clinicRepository: IClinicRepository
) {
    /**
     * Executes the use case to retrieve clinic details.
     *
     * @param link The unique link or identifier of the clinic.
     * @param isLocated A flag indicating whether the clinic is located/geocoded.
     * @return The [ClinicDomain] containing detailed clinic information.
     */
    suspend operator fun invoke(link: String, isLocated: Boolean): ClinicDomain =
        clinicRepository.getClinicInfo(link, isLocated)
}