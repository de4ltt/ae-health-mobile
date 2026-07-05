package feo.health.catalog.domain.use_case.search

import feo.health.catalog.domain.model.CoordsDomain
import feo.health.catalog.domain.repository.ISearchRepository
import javax.inject.Inject

/**
 * Use case for retrieving geographic coordinates for a given physical address.
 *
 * @property searchRepository The repository used to perform search and geocoding operations.
 */
class GetCoordsForAddressUseCase @Inject constructor(
    private val searchRepository: ISearchRepository
) {
    /**
     * Executes the use case to geocode an address into coordinates.
     *
     * @param address The address string to resolve.
     * @return The [CoordsDomain] containing latitude and longitude.
     */
    suspend operator fun invoke(address: String): CoordsDomain =
        searchRepository.getCoordsForAddress(address)
}