package feo.health.catalog.domain.model

data class SearchDomain(
    val doctors: List<DoctorDomain>,
    val clinics: List<ClinicDomain>,
    val services: List<ServiceDomain>
)
