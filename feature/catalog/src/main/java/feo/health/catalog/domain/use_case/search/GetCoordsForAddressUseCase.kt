package feo.health.catalog.domain.use_case.search

import feo.health.catalog.domain.model.CoordsDomain
import feo.health.catalog.domain.repository.ISearchRepository
import javax.inject.Inject

class GetCoordsForAddressUseCase @Inject constructor(
    private val searchRepository: ISearchRepository
) {
    suspend operator fun invoke(address: String): CoordsDomain =
        searchRepository.getCoordsForAddress(address)
}