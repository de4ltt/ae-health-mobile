package feo.health.catalog.doctor.api

import feo.health.catalog.doctor.dto.DoctorDto
import feo.health.network.model.NetworkResult

interface IDoctorApi {
    suspend fun getDoctors(q: String): NetworkResult<List<DoctorDto>>
    suspend fun getDoctorInfo(link: String): NetworkResult<DoctorDto>
    suspend fun getDoctorsBySpeciality(speciality: String): NetworkResult<List<DoctorDto>>
}