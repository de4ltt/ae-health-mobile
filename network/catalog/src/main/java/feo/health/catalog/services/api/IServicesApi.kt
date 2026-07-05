package feo.health.catalog.services.api

import feo.health.catalog.clinic.dto.ClinicDto
import feo.health.catalog.services.dto.ServiceDto
import feo.health.network.model.NetworkResult

/**
 * API contract for searching medical services directory listings and retrieving matching clinics.
 */
interface IServicesApi {
    /**
     * Search medical services matching keyword.
     *
     * @param q Text keyword.
     * @return [NetworkResult] wrapping matched [ServiceDto] list.
     */
    suspend fun getServices(q: String): NetworkResult<List<ServiceDto>>

    /**
     * Fetch list of clinics associated with specific medical service.
     *
     * @param link Service link identifier.
     * @return [NetworkResult] wrapping clinic DTO list.
     */
    suspend fun getClinicsByService(link: String): NetworkResult<List<ClinicDto>>
}