package feo.health.catalog.data.repository

import feo.health.catalog.clinic.api.IClinicApi
import feo.health.catalog.clinic.dto.ClinicDto
import feo.health.catalog.data.mapper.toDomain
import feo.health.catalog.doctor.dto.DoctorDto
import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.domain.model.DoctorDomain
import feo.health.catalog.domain.repository.IClinicRepository
import feo.health.network.model.mapResult
import javax.inject.Inject

class ClinicRepository @Inject constructor(
    private val clinicApi: IClinicApi
) : IClinicRepository {

    override suspend fun getClinics(
        q: String,
        isLocated: Boolean
    ): List<ClinicDomain> = clinicApi
        .getClinics(q = q, isLocated = isLocated)
        .mapResult(List<ClinicDto>::toDomain)

    override suspend fun getClinicsByType(link: String): List<ClinicDomain> = clinicApi
        .getClinicsByType(link = link)
        .mapResult(List<ClinicDto>::toDomain)

    override suspend fun getClinicInfo(
        link: String,
        isLocated: Boolean
    ): ClinicDomain = clinicApi
        .getClinicInfo(link = link, isLocated = isLocated)
        .mapResult(ClinicDto::toDomain)

    override suspend fun getClinicDoctors(link: String): List<DoctorDomain> = clinicApi
        .getClinicDoctors(link = link)
        .mapResult(List<DoctorDto>::toDomain)
}