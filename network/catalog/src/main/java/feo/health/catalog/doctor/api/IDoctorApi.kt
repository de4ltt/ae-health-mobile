package feo.health.catalog.doctor.api

import feo.health.catalog.doctor.dto.DoctorDto
import feo.health.network.model.NetworkResult

/**
 * API contract for searching doctor specialized listings and retrieving details.
 */
interface IDoctorApi {
    /**
     * Search doctors matching keyword.
     *
     * @param q Text keyword.
     * @return [NetworkResult] wrapping matched [DoctorDto] list.
     */
    suspend fun getDoctors(q: String): NetworkResult<List<DoctorDto>>

    /**
     * Fetch details profile of specific doctor.
     *
     * @param link Doctor details key link.
     * @return [NetworkResult] wrapping doctor details DTO.
     */
    suspend fun getDoctorInfo(link: String): NetworkResult<DoctorDto>

    /**
     * Fetch list of doctors belonging to specialized categories.
     *
     * @param speciality Specialty link identifier.
     * @return [NetworkResult] wrapping doctor DTO list.
     */
    suspend fun getDoctorsBySpeciality(speciality: String): NetworkResult<List<DoctorDto>>
}