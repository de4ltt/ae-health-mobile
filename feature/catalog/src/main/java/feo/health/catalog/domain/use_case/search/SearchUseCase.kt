package feo.health.catalog.domain.use_case.search

import feo.health.catalog.domain.model.SearchDomain
import feo.health.catalog.domain.repository.ISearchRepository
import javax.inject.Inject

/**
 * Use case for performing a general unified search across the health catalog.
 *
 * @property searchRepository The repository used to perform search operations.
 */
class SearchUseCase @Inject constructor(
    private val searchRepository: ISearchRepository
) {
    /**
     * Executes the use case to perform a unified search.
     *
     * @param q The search query string.
     * @param isLocated A flag indicating if location-based filtering should be applied.
     * @return The [SearchDomain] containing unified search results.
     */
    suspend operator fun invoke(q: String, isLocated: Boolean): SearchDomain =
        searchRepository.search(q, isLocated)
}