package feo.health.catalog.presentation.model

data class Search(
    val doctors: List<ICatalog.Doctor>,
    val clinics: List<ICatalog.Clinic>,
    val services: List<ICatalog.Service>
)
