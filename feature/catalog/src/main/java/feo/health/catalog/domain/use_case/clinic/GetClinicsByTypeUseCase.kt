package feo.health.catalog.domain.use_case.clinic

import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.domain.repository.IClinicRepository
import javax.inject.Inject

/**
 * Use case for retrieving a list of clinics filtered by a specific type or category link.
 *
 * @property clinicRepository The repository used to access clinic data.
 */
class GetClinicsByTypeUseCase @Inject constructor(
    private val clinicRepository: IClinicRepository
) {
    /**
     * Executes the use case to retrieve clinics of a specific type.
     *
     * @param link The unique link or identifier for the clinic type/category.
     * @return A list of [ClinicDomain] matching the specified clinic type.
     */
    suspend operator fun invoke(link: String): List<ClinicDomain> =
        clinicRepository.getClinicsByType(link)
}