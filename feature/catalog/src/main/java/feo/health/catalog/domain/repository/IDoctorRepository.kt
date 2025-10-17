package feo.health.catalog.domain.repository

import feo.health.catalog.domain.model.DoctorDomain

interface IDoctorRepository {
    suspend fun getDoctors(q: String): List<DoctorDomain>
    suspend fun getDoctorInfo(link: String): DoctorDomain
    suspend fun getDoctorsBySpeciality(speciality: String): List<DoctorDomain>
}