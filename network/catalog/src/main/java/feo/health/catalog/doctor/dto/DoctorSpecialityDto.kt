package feo.health.catalog.doctor.dto

import kotlinx.serialization.Serializable

@Serializable
data class DoctorSpecialityDto(
    val name: String,
    val link: String?
)
