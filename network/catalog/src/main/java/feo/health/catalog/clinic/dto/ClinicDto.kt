package feo.health.catalog.clinic.dto

import feo.health.catalog.search.dto.ReviewDto
import kotlinx.serialization.Serializable

/**
 * Data transfer object mapping clinical organization properties returned by remote endpoints.
 *
 * @property name Clinic organization name.
 * @property link Relative navigation route link.
 * @property address Clinic coordinates address, if available.
 * @property phoneNumber Contact dialing number, if available.
 * @property imageUri Endpoint location of the clinic visual banner.
 * @property itemType Visual item styling identifier tag.
 * @property reviews User review list payload metrics, if available.
 */
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
