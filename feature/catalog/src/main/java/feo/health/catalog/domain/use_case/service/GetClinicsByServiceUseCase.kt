package feo.health.catalog.domain.use_case.service

import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.domain.repository.IServicesRepository
import javax.inject.Inject

/**
 * Use case for retrieving a list of clinics offering a specific service.
 *
 * @property servicesRepository The repository used to access services and clinic data.
 */
class GetClinicsByServiceUseCase @Inject constructor(
    private val servicesRepository: IServicesRepository
) {
    /**
     * Executes the use case to retrieve clinics that provide a specific service.
     *
     * @param link The unique link or identifier for the service.
     * @return A list of [ClinicDomain] representing the clinics providing the service.
     */
    suspend operator fun invoke(link: String): List<ClinicDomain> =
        servicesRepository.getClinicsByService(link)
}