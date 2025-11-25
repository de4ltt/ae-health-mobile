package feo.health.user.components.domain.use_case.favourite

import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.domain.repository.IFavouritesRepository
import javax.inject.Inject

class AddFavouriteUseCase @Inject constructor(
    private val favouritesRepository: IFavouritesRepository
) {
    suspend operator fun invoke(
        catalogItemDomain: CatalogItemDomain
    ) = favouritesRepository.addFavourite(catalogItemDomain)
}