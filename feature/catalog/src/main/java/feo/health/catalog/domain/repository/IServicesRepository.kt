package feo.health.catalog.domain.repository

import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.domain.model.ServiceDomain

/**
 * Repository interface for managing and retrieving service-related data.
 *
 * This repository defines operations to query medical services and find clinics offering specific services.
 */
interface IServicesRepository {
    /**
     * Retrieves a list of medical services matching a search query.
     *
     * @param q The search query string.
     * @return A list of [ServiceDomain] objects matching the query.
     */
    suspend fun getServices(q: String): List<ServiceDomain>

    /**
     * Retrieves a list of clinics that offer a specific service.
     *
     * @param link The unique link/identifier of the service.
     * @return A list of [ClinicDomain] objects offering the specified service.
     */
    suspend fun getClinicsByService(link: String): List<ClinicDomain>
}