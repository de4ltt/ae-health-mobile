package feo.health.catalog.services.api

import feo.health.catalog.clinic.dto.ClinicDto
import feo.health.catalog.services.dto.ServiceDto
import feo.health.network.model.NetworkResult

interface IServicesApi {
    suspend fun getServices(q: String): NetworkResult<List<ServiceDto>>
    suspend fun getClinicsByService(link: String): NetworkResult<List<ClinicDto>>
}