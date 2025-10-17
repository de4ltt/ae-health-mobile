package feo.health.catalog.domain.use_case.doctor

import feo.health.catalog.domain.model.DoctorDomain
import feo.health.catalog.domain.repository.IDoctorRepository
import javax.inject.Inject

class GetDoctorsUseCase @Inject constructor(
    private val doctorRepository: IDoctorRepository
) {
    suspend operator fun invoke(q: String): List<DoctorDomain> =
        doctorRepository.getDoctors(q)
}