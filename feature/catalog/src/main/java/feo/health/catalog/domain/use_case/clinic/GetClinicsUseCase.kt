package feo.health.catalog.domain.use_case.clinic

import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.domain.repository.IClinicRepository
import javax.inject.Inject

class GetClinicsUseCase @Inject constructor(
    private val clinicRepository: IClinicRepository
) {
    suspend operator fun invoke(q: String, isLocated: Boolean): List<ClinicDomain> =
        clinicRepository.getClinics(q, isLocated)
}

