package feo.health.catalog.presentation.model

/**
 * Data class representing the search results container.
 *
 * @property doctors The list of doctors matching the search criteria.
 * @property clinics The list of clinics matching the search criteria.
 * @property services The list of services matching the search criteria.
 */
data class Search(
    val doctors: List<ICatalog.Doctor>,
    val clinics: List<ICatalog.Clinic>,
    val services: List<ICatalog.Service>
)
