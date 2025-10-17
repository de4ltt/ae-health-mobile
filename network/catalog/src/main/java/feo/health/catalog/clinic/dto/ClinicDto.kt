package feo.health.catalog.clinic.dto

import feo.health.catalog.search.dto.ReviewDto
import kotlinx.serialization.Serializable

@Serializable
data class ClinicDto(
    val name: String,
    val link: String,
    val address: String?,
    val phoneNumber: String?,
    val imageUri: String?,
    val itemType: String,
    val reviews: List<ReviewDto>?
)
