package feo.health.catalog.search.dto

import feo.health.catalog.clinic.dto.ClinicDto
import feo.health.catalog.doctor.dto.DoctorDto
import feo.health.catalog.services.dto.ServiceDto
import kotlinx.serialization.Serializable

/**
 * Data transfer object mapping search directory results containing matched doctors, clinics, and services.
 *
 * @property doctors List of matching doctor DTOs.
 * @property clinics List of matching clinic DTOs.
 * @property services List of matching medical service DTOs.
 */
@Serializable
data class SearchDto(
    val doctors: List<DoctorDto>,
    val clinics: List<ClinicDto>,
    val services: List<ServiceDto>
)