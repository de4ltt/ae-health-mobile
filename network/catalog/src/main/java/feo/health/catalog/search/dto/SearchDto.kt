package feo.health.catalog.search.dto

import feo.health.catalog.clinic.dto.ClinicDto
import feo.health.catalog.doctor.dto.DoctorDto
import feo.health.catalog.services.dto.ServiceDto
import kotlinx.serialization.Serializable

@Serializable
data class SearchDto(
    val doctors: List<DoctorDto>,
    val clinics: List<ClinicDto>,
    val services: List<ServiceDto>
)
