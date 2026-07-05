package feo.health.catalog.domain.use_case.pharmacy

import feo.health.catalog.domain.model.PharmacyDomain
import feo.health.catalog.domain.repository.IPharmacyRepository
import javax.inject.Inject

/**
 * Use case for logging or recording a visit to a pharmacy.
 *
 * @property pharmacyRepository The repository used to access pharmacy data.
 */
class VisitPharmacyUseCase @Inject constructor(
    private val pharmacyRepository: IPharmacyRepository
) {
    /**
     * Executes the use case to register a visit to a specific pharmacy.
     *
     * @param pharmacyDomain The pharmacy domain model representing the visited pharmacy.
     */
    suspend operator fun invoke(pharmacyDomain: PharmacyDomain) =
        pharmacyRepository.visitPharmacy(pharmacyDomain)
}