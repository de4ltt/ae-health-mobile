package feo.health.catalog.domain.repository

import feo.health.catalog.domain.model.CoordsDomain
import feo.health.catalog.domain.model.SearchDomain

interface ISearchRepository {
    suspend fun search(q: String, isLocated: Boolean): SearchDomain
    suspend fun getCoordsForAddress(address: String): CoordsDomain
}