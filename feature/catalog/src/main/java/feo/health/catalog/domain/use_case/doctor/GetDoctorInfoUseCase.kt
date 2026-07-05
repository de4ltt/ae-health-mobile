package feo.health.catalog.domain.use_case.doctor

import feo.health.catalog.domain.model.DoctorDomain
import feo.health.catalog.domain.repository.IDoctorRepository
import javax.inject.Inject

/**
 * Use case for retrieving detailed information about a specific doctor.
 *
 * @property doctorRepository The repository used to access doctor data.
 */
class GetDoctorInfoUseCase @Inject constructor(
    private val doctorRepository: IDoctorRepository
) {
    /**
     * Executes the use case to retrieve doctor details.
     *
     * @param link The unique link or identifier of the doctor.
     * @return The [DoctorDomain] containing detailed doctor information.
     */
    suspend operator fun invoke(link: String): DoctorDomain =
        doctorRepository.getDoctorInfo(link)
}