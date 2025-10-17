package feo.health.catalog.doctor.dto

import feo.health.catalog.search.dto.ReviewDto
import kotlinx.serialization.Serializable

@Serializable
data class DoctorDto(
    val name: String,
    val link: String,
    val specialities: List<DoctorSpeciality>?,
    val experience: Int?,
    val imageUri: String?,
    val rating: Double?,
    val itemType: String,
    val reviews: List<ReviewDto>?,
)
