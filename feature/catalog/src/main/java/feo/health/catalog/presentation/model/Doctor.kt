package feo.health.catalog.presentation.model

data class Doctor(
    val name: String,
    val link: String,
    val specialities: List<DoctorSpeciality>?,
    val experience: Int?,
    val imageUri: String?,
    val rating: Double?,
    val itemType: String,
    val reviews: List<Review>?,
)
