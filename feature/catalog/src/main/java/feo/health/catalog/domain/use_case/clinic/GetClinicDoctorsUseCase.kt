package feo.health.catalog.domain.use_case.clinic

import feo.health.catalog.domain.model.DoctorDomain
import feo.health.catalog.domain.repository.IClinicRepository
import javax.inject.Inject

/**
 * Use case for retrieving the list of doctors associated with a specific clinic.
 *
 * @property clinicRepository The repository used to access clinic and doctor data.
 */
class GetClinicDoctorsUseCase @Inject constructor(
    private val clinicRepository: IClinicRepository
) {
    /**
     * Executes the use case to retrieve the doctors of a clinic.
     *
     * @param link The unique link or identifier of the clinic.
     * @return A list of [DoctorDomain] representing the clinic's doctors.
     */
    suspend operator fun invoke(link: String): List<DoctorDomain> =
        clinicRepository.getClinicDoctors(link)
}