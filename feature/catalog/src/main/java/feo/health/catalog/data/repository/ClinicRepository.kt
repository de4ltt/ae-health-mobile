package feo.health.catalog.data.repository

import feo.health.catalog.clinic.api.IClinicApi
import feo.health.catalog.clinic.dto.ClinicDto
import feo.health.catalog.data.mapper.ClinicDtoToClinicDomainMapper.toClinicDomain
import feo.health.catalog.data.mapper.ClinicDtoToClinicDomainMapper.toClinicDomainList
import feo.health.catalog.data.mapper.DoctorDtoToDoctorDomainMapper.toDoctorDomainList
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
        .mapResult { it.toClinicDomainList() }

    override suspend fun getClinicsByType(link: String): List<ClinicDomain> = clinicApi
        .getClinicsByType(link = link)
        .mapResult{ it.toClinicDomainList() }

    override suspend fun getClinicInfo(
        link: String,
        isLocated: Boolean
    ): ClinicDomain = clinicApi
        .getClinicInfo(link = link, isLocated = isLocated)
        .mapResult{ it.toClinicDomain() }

    override suspend fun getClinicDoctors(link: String): List<DoctorDomain> = clinicApi
        .getClinicDoctors(link = link)
        .mapResult{ it.toDoctorDomainList() }
}