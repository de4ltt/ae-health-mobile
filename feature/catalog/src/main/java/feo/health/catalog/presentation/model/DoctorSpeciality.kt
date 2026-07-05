package feo.health.catalog.presentation.model

/**
 * Data class representing a doctor's specialty.
 *
 * @property name The name of the specialty.
 * @property link The details link/resource path for the specialty, or null if unavailable.
 */
data class DoctorSpeciality(
    val name: String,
    val link: String?
)
