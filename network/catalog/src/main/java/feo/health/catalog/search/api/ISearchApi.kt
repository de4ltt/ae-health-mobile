package feo.health.catalog.search.api

import feo.health.catalog.search.dto.CoordsDto
import feo.health.catalog.search.dto.SearchDto
import feo.health.network.model.NetworkResult

interface ISearchApi {
    suspend fun search(q: String, isLocated: Boolean): NetworkResult<SearchDto>
    suspend fun getCoordsForAddress(address: String): NetworkResult<CoordsDto>
}