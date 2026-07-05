package feo.health.catalog.data.repository

import feo.health.catalog.domain.model.CoordsDomain
import feo.health.catalog.domain.model.SearchDomain
import feo.health.catalog.domain.repository.ISearchRepository
import feo.health.catalog.search.api.ISearchApi
import feo.health.network.model.mapResult
import javax.inject.Inject
import feo.health.catalog.data.mapper.CoordsDomainToCoordsDtoMapper.toDomain as toCoordsDomain
import feo.health.catalog.data.mapper.SearchDtoToSearchDomainMapper.toDomain as toSearchDomain

/**
 * Repository interface implementation managing remote search query matches and geocoding conversions.
 *
 * @property searchApi Remote search and geocoding API client.
 */
class SearchRepository @Inject constructor(
    private val searchApi: ISearchApi
) : ISearchRepository {

    /**
     * Queries matched medical services, doctors, and clinics matching text query.
     *
     * @param q Text keyword matching target items.
     * @param isLocated Coordinate bound filter boundaries flag.
     * @return Domain [SearchDomain] catalog matching details.
     */
    override suspend fun search(
        q: String,
        isLocated: Boolean
    ): SearchDomain = searchApi
        .search(q = q, isLocated = isLocated)
        .mapResult { it.toSearchDomain() }

    /**
     * Converts a physical address description string to longitude and latitude coordinates map.
     *
     * @param address Target physical address query string.
     * @return Domain geocoded coordinate location properties.
     */
    override suspend fun getCoordsForAddress(address: String): CoordsDomain =
        searchApi.getCoordsForAddress(address).mapResult { it.toCoordsDomain() }
}