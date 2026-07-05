package feo.health.catalog.domain.model

/**
 * Domain representation of Catalog Search Results.
 *
 * This data class aggregates search results from different categories (doctors, clinics, services)
 * under a single object.
 *
 * @property doctors The list of doctors matching the search criteria.
 * @property clinics The list of clinics matching the search criteria.
 * @property services The list of services matching the search criteria.
 */
data class SearchDomain(
    val doctors: List<DoctorDomain>,
    val clinics: List<ClinicDomain>,
    val services: List<ServiceDomain>
)
