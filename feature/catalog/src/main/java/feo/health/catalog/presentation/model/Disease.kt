package feo.health.catalog.presentation.model

/**
 * Data class representing a disease catalog entry.
 *
 * @property name The name of the disease.
 * @property link The details link/resource path for the disease.
 */
data class Disease(
    val name: String,
    val link: String
)
