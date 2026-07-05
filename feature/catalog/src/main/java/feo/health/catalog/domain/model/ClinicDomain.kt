package feo.health.catalog.domain.model

/**
 * Domain representation of a Clinic.
 *
 * This data class contains core information about a clinic, including its name, contact
 * details, associated reviews, and other catalog metadata.
 *
 * @property name The name of the clinic.
 * @property link The web or detail link for the clinic.
 * @property address The physical address of the clinic, if available.
 * @property phoneNumber The contact phone number of the clinic, if available.
 * @property imageUri The URI or URL of the clinic's image, if available.
 * @property itemType The catalog item type identifier.
 * @property reviews The list of user reviews for this clinic, if available.
 */
data class ClinicDomain(
    val name: String,
    val link: String,
    val address: String?,
    val phoneNumber: String?,
    val imageUri: String?,
    val itemType: String,
    val reviews: List<ReviewDomain>?
)
