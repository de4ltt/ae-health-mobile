package feo.health.catalog.data.repository

import feo.health.catalog.data.mapper.toDomain
import feo.health.catalog.domain.model.SearchDomain
import feo.health.catalog.domain.repository.ISearchRepository
import feo.health.catalog.search.api.ISearchApi
import feo.health.catalog.search.dto.SearchDto
import feo.health.network.model.mapResult
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchApi: ISearchApi
): ISearchRepository {

    override suspend fun search(
        q: String,
        isLocated: Boolean
    ): SearchDomain = searchApi
        .search(q = q, isLocated = isLocated)
        .mapResult(SearchDto::toDomain)
}