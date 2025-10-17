package feo.health.catalog.domain.repository

import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.domain.model.ServiceDomain

interface IServicesRepository {
    suspend fun getServices(q: String): List<ServiceDomain>
    suspend fun getClinicsByService(link: String): List<ClinicDomain>
}