package feo.health.catalog.doctor.dto

import feo.health.catalog.search.dto.ReviewDto
import kotlinx.serialization.Serializable

@Serializable
data class DoctorDto(
    val name: String,
    val link: String,
    val specialities: List<DoctorSpecialityDto>? = null,
    val experience: Int? = null,
    val imageUri: String? = null,
    val rating: Double? = null,
    val itemType: String,
    val reviews: List<ReviewDto>? = null,
)
