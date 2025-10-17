package feo.health.catalog.domain.use_case.clinic

import feo.health.catalog.domain.model.DoctorDomain
import feo.health.catalog.domain.repository.IClinicRepository
import javax.inject.Inject

class GetClinicDoctorsUseCase @Inject constructor(
    private val clinicRepository: IClinicRepository
) {
    suspend operator fun invoke(link: String): List<DoctorDomain> =
        clinicRepository.getClinicDoctors(link)
}