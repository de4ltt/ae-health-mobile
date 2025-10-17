package feo.health.catalog.domain.model

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
