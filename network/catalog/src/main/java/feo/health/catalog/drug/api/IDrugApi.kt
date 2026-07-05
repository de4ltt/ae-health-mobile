package feo.health.catalog.drug.api

import feo.health.catalog.drug.dto.DrugDto
import feo.health.network.model.NetworkResult

/**
 * API contract for searching drug medication listings and retrieving details.
 */
interface IDrugApi {
    /**
     * Search drug medications matching keyword.
     *
     * @param q Text keyword.
     * @return [NetworkResult] wrapping matched [DrugDto] list.
     */
    suspend fun getDrugs(q: String): NetworkResult<List<DrugDto>>

    /**
     * Fetch details of specific drug medication.
     *
     * @param link Drug details key link.
     * @return [NetworkResult] wrapping drug details DTO.
     */
    suspend fun getDrugInfo(link: String): NetworkResult<DrugDto>
}