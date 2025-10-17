package feo.health.catalog.data.repository

import feo.health.catalog.data.mapper.toDomain
import feo.health.catalog.doctor.api.IDoctorApi
import feo.health.catalog.doctor.dto.DoctorDto
import feo.health.catalog.domain.model.DoctorDomain
import feo.health.catalog.domain.repository.IDoctorRepository
import feo.health.network.model.mapResult
import javax.inject.Inject

class DoctorRepository @Inject constructor(
    private val doctorApi: IDoctorApi
): IDoctorRepository {

    override suspend fun getDoctors(q: String): List<DoctorDomain> = doctorApi
        .getDoctors(q = q)
        .mapResult(List<DoctorDto>::toDomain)

    override suspend fun getDoctorInfo(link: String): DoctorDomain = doctorApi
        .getDoctorInfo(link = link)
        .mapResult(DoctorDto::toDomain)

    override suspend fun getDoctorsBySpeciality(speciality: String): List<DoctorDomain> = doctorApi
        .getDoctorsBySpeciality(speciality = speciality)
        .mapResult(List<DoctorDto>::toDomain)
}