package feo.health.catalog.domain.model

/**
 * Domain representation of a Disease.
 *
 * This data class contains basic search or catalog details for a disease entry.
 *
 * @property name The name of the disease.
 * @property link The web or detail link associated with the disease.
 */
data class DiseaseDomain(
    val name: String,
    val link: String
)
