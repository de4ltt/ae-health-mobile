package feo.health.catalog.domain.model

/**
 * Domain representation of a Medical Service.
 *
 * This data class represents a service in the catalog (e.g. diagnostics, consultation).
 *
 * @property name The name of the service.
 * @property link The detail link for the service.
 * @property itemType The catalog item type identifier, defaults to "service".
 */
data class ServiceDomain(
    val name: String,
    val link: String,
    val itemType: String = "service"
)
