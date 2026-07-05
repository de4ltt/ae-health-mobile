package feo.health.catalog.domain.model

/**
 * Domain representation of a Doctor.
 *
 * This data class contains details about a doctor, including name, specialties, years
 * of experience, rating, and user reviews.
 *
 * @property name The doctor's name.
 * @property link The profile or booking link for the doctor.
 * @property specialities The specialties or fields of expertise of the doctor, if available.
 * @property experience The years of experience of the doctor, if available.
 * @property imageUri The profile image URI of the doctor, if available.
 * @property rating The average rating of the doctor, if available.
 * @property itemType The catalog item type identifier.
 * @property reviews The reviews associated with the doctor, if available.
 */
data class DoctorDomain(
    val name: String,
    val link: String,
    val specialities: List<DoctorSpecialityDomain>?,
    val experience: Int?,
    val imageUri: String?,
    val rating: Double?,
    val itemType: String,
    val reviews: List<ReviewDomain>?,
)
