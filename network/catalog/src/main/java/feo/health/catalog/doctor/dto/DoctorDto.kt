package feo.health.catalog.doctor.dto

import feo.health.catalog.search.dto.ReviewDto
import kotlinx.serialization.Serializable

/**
 * Data transfer object mapping specialist doctor details returned by remote endpoints.
 *
 * @property name Doctor's full name.
 * @property link Navigation path string.
 * @property specialities List of medical specialization DTOs, if available.
 * @property experience Years of active medical practice, if available.
 * @property imageUri Endpoint location of doctor's avatar image.
 * @property rating Average user feedback rating score, if available.
 * @property itemType Visual item styling identifier tag.
 * @property reviews User review list payload metrics, if available.
 */
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
