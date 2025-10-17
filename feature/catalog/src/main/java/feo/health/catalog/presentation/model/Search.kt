package feo.health.catalog.presentation.model

data class Search(
    val doctors: List<Doctor>,
    val clinics: List<ClinicDomain>,
    val services: List<Service>
)
