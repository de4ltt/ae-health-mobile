package feo.health.catalog.clinic.api

import feo.health.catalog.clinic.dto.ClinicDto
import feo.health.catalog.doctor.dto.DoctorDto
import feo.health.network.model.NetworkResult

interface IClinicApi {
    suspend fun getClinics(q: String, isLocated: Boolean = true): NetworkResult<List<ClinicDto>>
    suspend fun getClinicsByType(link: String): NetworkResult<List<ClinicDto>>
    suspend fun getClinicInfo(link: String, isLocated: Boolean = true): NetworkResult<ClinicDto>
    suspend fun getClinicDoctors(link: String): NetworkResult<List<DoctorDto>>
}