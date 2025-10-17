package feo.health.catalog.domain.use_case.clinic

import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.domain.repository.IClinicRepository
import javax.inject.Inject

class GetClinicInfoUseCase @Inject constructor(
    private val clinicRepository: IClinicRepository
) {
    suspend operator fun invoke(link: String, isLocated: Boolean): ClinicDomain =
        clinicRepository.getClinicInfo(link, isLocated)
}