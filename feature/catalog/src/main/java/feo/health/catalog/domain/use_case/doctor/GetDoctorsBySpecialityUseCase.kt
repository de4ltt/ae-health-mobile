package feo.health.catalog.domain.use_case.doctor

import feo.health.catalog.domain.model.DoctorDomain
import feo.health.catalog.domain.repository.IDoctorRepository
import javax.inject.Inject

/**
 * Use case for retrieving a list of doctors filtered by their speciality.
 *
 * @property doctorRepository The repository used to access doctor data.
 */
class GetDoctorsBySpecialityUseCase @Inject constructor(
    private val doctorRepository: IDoctorRepository
) {
    /**
     * Executes the use case to retrieve doctors of a specific speciality.
     *
     * @param speciality The speciality category or identifier of the doctors.
     * @return A list of [DoctorDomain] representing the doctors with the specified speciality.
     */
    suspend operator fun invoke(speciality: String): List<DoctorDomain> =
        doctorRepository.getDoctorsBySpeciality(speciality)
}