package feo.health.catalog.domain.model

/**
 * Domain representation of a Doctor's Specialty.
 *
 * This data class represents a medical specialty associated with a doctor.
 *
 * @property name The name of the specialty.
 * @property link The detail link associated with the specialty, if available.
 */
data class DoctorSpecialityDomain(
    val name: String,
    val link: String?
)
