package feo.health.catalog.search.api

import feo.health.catalog.search.dto.CoordsDto
import feo.health.catalog.search.dto.SearchDto
import feo.health.network.model.NetworkResult

/**
 * API contract for searching multiple catalogs (doctors, clinics, services) and resolving physical geocoordinates.
 */
interface ISearchApi {
    /**
     * Search multiple catalogs matching query text keyword.
     *
     * @param q Text keyword.
     * @param isLocated Coordinate boundary filter flag.
     * @return [NetworkResult] wrapping search results matching catalogs.
     */
    suspend fun search(q: String, isLocated: Boolean): NetworkResult<SearchDto>

    /**
     * Decode physical coordinates location attributes from input address string.
     *
     * @param address Full address description text.
     * @return [NetworkResult] wrapping geocoded coordinate DTO.
     */
    suspend fun getCoordsForAddress(address: String): NetworkResult<CoordsDto>
}