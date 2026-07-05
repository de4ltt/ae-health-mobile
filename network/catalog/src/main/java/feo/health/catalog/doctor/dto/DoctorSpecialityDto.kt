package feo.health.catalog.doctor.dto

import kotlinx.serialization.Serializable

/**
 * Data transfer object mapping doctor medical specialization attributes.
 *
 * @property name Specialization name.
 * @property link Navigational identifier link.
 */
@Serializable
data class DoctorSpecialityDto(
    val name: String,
    val link: String?
)
