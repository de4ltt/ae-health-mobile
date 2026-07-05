package feo.health.catalog.domain.use_case.pharmacy

import feo.health.catalog.domain.model.PharmacyDomain
import feo.health.catalog.domain.repository.IPharmacyRepository
import javax.inject.Inject

/**
 * Use case for retrieving detailed information about a pharmacy by its ID.
 *
 * @property pharmacyRepository The repository used to access pharmacy data.
 */
class GetPharmacyByIdUseCase @Inject constructor(
    private val pharmacyRepository: IPharmacyRepository
) {
    /**
     * Executes the use case to retrieve details of a specific pharmacy.
     *
     * @param id The unique identifier of the pharmacy.
     * @return The [PharmacyDomain] containing detailed pharmacy information.
     */
    suspend operator fun invoke(id: Long): PharmacyDomain =
        pharmacyRepository.getPharmacyById(id)
}