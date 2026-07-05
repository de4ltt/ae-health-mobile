package feo.health.catalog.domain.repository

import feo.health.catalog.domain.model.DoctorDomain

/**
 * Repository interface for managing and retrieving doctor-related data.
 *
 * This repository defines operations to query doctors, retrieve individual doctor details,
 * and filter doctors by their medical specialty.
 */
interface IDoctorRepository {
    /**
     * Retrieves a list of doctors matching a search query.
     *
     * @param q The search query string.
     * @return A list of [DoctorDomain] objects matching the query.
     */
    suspend fun getDoctors(q: String): List<DoctorDomain>

    /**
     * Retrieves detailed information about a specific doctor.
     *
     * @param link The unique profile link/identifier of the doctor.
     * @return The [DoctorDomain] containing doctor details.
     */
    suspend fun getDoctorInfo(link: String): DoctorDomain

    /**
     * Retrieves a list of doctors who specialize in a specific field.
     *
     * @param speciality The medical specialty filter.
     * @return A list of [DoctorDomain] objects belonging to the specialty.
     */
    suspend fun getDoctorsBySpeciality(speciality: String): List<DoctorDomain>
}