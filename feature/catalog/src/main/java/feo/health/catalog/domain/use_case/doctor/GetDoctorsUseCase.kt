package feo.health.catalog.domain.use_case.doctor

import feo.health.catalog.domain.model.DoctorDomain
import feo.health.catalog.domain.repository.IDoctorRepository
import javax.inject.Inject

/**
 * Use case for retrieving a list of doctors, optionally filtered by a search query.
 *
 * @property doctorRepository The repository used to access doctor data.
 */
class GetDoctorsUseCase @Inject constructor(
    private val doctorRepository: IDoctorRepository
) {
    /**
     * Executes the use case to search or retrieve doctors.
     *
     * @param q The search query string to filter doctors.
     * @return A list of [DoctorDomain] matching the criteria.
     */
    suspend operator fun invoke(q: String): List<DoctorDomain> =
        doctorRepository.getDoctors(q)
}