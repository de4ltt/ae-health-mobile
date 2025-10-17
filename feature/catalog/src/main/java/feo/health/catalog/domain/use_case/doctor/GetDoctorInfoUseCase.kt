package feo.health.catalog.domain.use_case.doctor

import feo.health.catalog.domain.model.DoctorDomain
import feo.health.catalog.domain.repository.IDoctorRepository
import javax.inject.Inject

class GetDoctorInfoUseCase @Inject constructor(
    private val doctorRepository: IDoctorRepository
) {
    suspend operator fun invoke(link: String): DoctorDomain =
        doctorRepository.getDoctorInfo(link)
}