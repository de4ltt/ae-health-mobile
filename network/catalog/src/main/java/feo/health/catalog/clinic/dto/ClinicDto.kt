package feo.health.catalog.clinic.dto

import feo.health.catalog.search.dto.ReviewDto
import kotlinx.serialization.Serializable

@Serializable
data class ClinicDto(
    val name: String,
    val link: String,
    val address: String? = null,
    val phoneNumber: String? = null,
    val imageUri: String? = null,
    val itemType: String,
    val reviews: List<ReviewDto>? = null
)
