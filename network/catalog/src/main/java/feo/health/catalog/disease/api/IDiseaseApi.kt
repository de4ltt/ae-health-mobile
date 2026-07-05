package feo.health.catalog.disease.api

import feo.health.catalog.disease.dto.DiseaseDto
import feo.health.network.model.NetworkResult

/**
 * API contract for searching diseases listings and retrieving detail files.
 */
interface IDiseaseApi {
    /**
     * Search diseases matching keyword.
     *
     * @param q Text keyword.
     * @return [NetworkResult] wrapping matched [DiseaseDto] list.
     */
    suspend fun getDiseases(q: String): NetworkResult<List<DiseaseDto>>

    /**
     * Fetch description data of specific disease.
     *
     * @param link Disease details key link.
     * @return [NetworkResult] wrapping detailed text description.
     */
    suspend fun getDiseaseInfo(link: String): NetworkResult<String>
}