package feo.health.catalog.domain.repository

import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.domain.model.DoctorDomain

/**
 * Repository interface for managing and retrieving clinic-related data.
 *
 * This repository defines operations to query clinics, retrieve clinic details,
 * and fetch doctors associated with a clinic.
 */
interface IClinicRepository {
    /**
     * Retrieves a list of clinics matching a search query.
     *
     * @param q The search query string.
     * @param isLocated Flag indicating whether the location should be factored into the query.
     * @return A list of [ClinicDomain] objects matching the query.
     */
    suspend fun getClinics(q: String, isLocated: Boolean = true): List<ClinicDomain>

    /**
     * Retrieves a list of clinics filtered by a specific type or category link.
     *
     * @param link The specific type or category identifier link.
     * @return A list of [ClinicDomain] objects belonging to the type.
     */
    suspend fun getClinicsByType(link: String): List<ClinicDomain>

    /**
     * Retrieves detailed information about a specific clinic.
     *
     * @param link The unique link/identifier of the clinic.
     * @param isLocated Flag indicating whether location coordinates are used.
     * @return The [ClinicDomain] containing clinic details.
     */
    suspend fun getClinicInfo(link: String, isLocated: Boolean = true): ClinicDomain

    /**
     * Retrieves a list of doctors associated with a specific clinic.
     *
     * @param link The unique link/identifier of the clinic.
     * @return A list of [DoctorDomain] representing the clinic's doctors.
     */
    suspend fun getClinicDoctors(link: String): List<DoctorDomain>
}