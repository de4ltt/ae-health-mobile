package feo.health.catalog.domain.use_case.service

import feo.health.catalog.domain.model.ServiceDomain
import feo.health.catalog.domain.repository.IServicesRepository
import javax.inject.Inject

class GetServicesUseCase @Inject constructor(
    private val servicesRepository: IServicesRepository
) {
    suspend operator fun invoke(q: String): List<ServiceDomain> =
        servicesRepository.getServices(q)
}