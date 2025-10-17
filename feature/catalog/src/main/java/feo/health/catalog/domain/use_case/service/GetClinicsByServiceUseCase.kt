package feo.health.catalog.domain.use_case.service

import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.domain.repository.IServicesRepository
import javax.inject.Inject

class GetClinicsByServiceUseCase @Inject constructor(
    private val servicesRepository: IServicesRepository
) {
    suspend operator fun invoke(link: String): List<ClinicDomain> =
        servicesRepository.getClinicsByService(link)
}