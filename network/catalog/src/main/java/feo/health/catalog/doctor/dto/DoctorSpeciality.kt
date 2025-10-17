package feo.health.catalog.doctor.dto

import kotlinx.serialization.Serializable

@Serializable
data class DoctorSpeciality(
    val name: String,
    val link: String?
)
