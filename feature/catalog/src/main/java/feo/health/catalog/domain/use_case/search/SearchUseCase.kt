package feo.health.catalog.domain.use_case.search

import feo.health.catalog.domain.model.SearchDomain
import feo.health.catalog.domain.repository.ISearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: ISearchRepository
) {
    suspend operator fun invoke(q: String, isLocated: Boolean): SearchDomain =
        searchRepository.search(q, isLocated)
}