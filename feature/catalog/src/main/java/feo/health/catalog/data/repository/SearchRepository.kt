package feo.health.catalog.data.repository

import feo.health.catalog.data.mapper.CoordsDomainToCoordsDtoMapper.toCoordsDomain
import feo.health.catalog.data.mapper.SearchDtoToSearchDomainMapper.toSearchDomain
import feo.health.catalog.domain.model.CoordsDomain
import feo.health.catalog.domain.model.SearchDomain
import feo.health.catalog.domain.repository.ISearchRepository
import feo.health.catalog.search.api.ISearchApi
import feo.health.network.model.mapResult
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchApi: ISearchApi
) : ISearchRepository {

    override suspend fun search(
        q: String,
        isLocated: Boolean
    ): SearchDomain = searchApi
        .search(q = q, isLocated = isLocated)
        .mapResult { it.toSearchDomain() }

    override suspend fun getCoordsForAddress(address: String): CoordsDomain =
        searchApi.getCoordsForAddress(address).mapResult { it.toCoordsDomain() }
}