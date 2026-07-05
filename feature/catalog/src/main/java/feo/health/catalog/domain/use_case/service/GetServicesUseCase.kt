package feo.health.catalog.domain.use_case.service

import feo.health.catalog.domain.model.ServiceDomain
import feo.health.catalog.domain.repository.IServicesRepository
import javax.inject.Inject

/**
 * Use case for retrieving a list of services, optionally filtered by a search query.
 *
 * @property servicesRepository The repository used to access services data.
 */
class GetServicesUseCase @Inject constructor(
    private val servicesRepository: IServicesRepository
) {
    /**
     * Executes the use case to search or retrieve services.
     *
     * @param q The search query string to filter services.
     * @return A list of [ServiceDomain] matching the criteria.
     */
    suspend operator fun invoke(q: String): List<ServiceDomain> =
        servicesRepository.getServices(q)
}