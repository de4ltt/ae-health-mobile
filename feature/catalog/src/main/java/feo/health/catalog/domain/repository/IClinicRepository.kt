package feo.health.catalog.domain.repository

import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.domain.model.DoctorDomain

interface IClinicRepository {
    suspend fun getClinics(q: String, isLocated: Boolean = true): List<ClinicDomain>
    suspend fun getClinicsByType(link: String): List<ClinicDomain>
    suspend fun getClinicInfo(link: String, isLocated: Boolean = true): ClinicDomain
    suspend fun getClinicDoctors(link: String): List<DoctorDomain>
}