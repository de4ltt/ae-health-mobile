package feo.health.catalog.clinic.api

import feo.health.catalog.clinic.dto.ClinicDto
import feo.health.catalog.doctor.dto.DoctorDto
import feo.health.network.model.NetworkResult

/**
 * API contract for fetching clinic listings and related staff information.
 */
interface IClinicApi {
    /**
     * Fetch clinics matching keyword search.
     *
     * @param q Text keyword.
     * @param isLocated Filters coordinates.
     * @return [NetworkResult] wrapping matching clinic DTO list.
     */
    suspend fun getClinics(q: String, isLocated: Boolean = true): NetworkResult<List<ClinicDto>>

    /**
     * Fetch clinics associated with specialized clinic categories.
     *
     * @param link Specialty link identifier.
     * @return [NetworkResult] wrapping clinic DTO list.
     */
    suspend fun getClinicsByType(link: String): NetworkResult<List<ClinicDto>>

    /**
     * Fetch details of single clinic.
     *
     * @param link Clinic details key link.
     * @param isLocated Coordinates filter.
     * @return [NetworkResult] wrapping clinic DTO.
     */
    suspend fun getClinicInfo(link: String, isLocated: Boolean = true): NetworkResult<ClinicDto>

    /**
     * Fetch list of doctors working in clinic.
     *
     * @param link Clinic details key link.
     * @return [NetworkResult] wrapping doctor list.
     */
    suspend fun getClinicDoctors(link: String): NetworkResult<List<DoctorDto>>
}