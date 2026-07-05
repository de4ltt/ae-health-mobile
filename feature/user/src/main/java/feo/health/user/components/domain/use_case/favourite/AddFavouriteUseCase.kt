package feo.health.user.components.domain.use_case.favourite

import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.domain.repository.IFavouritesRepository
import javax.inject.Inject

/**
 * Use case for adding a catalog item to the user's favourites.
 *
 * @property favouritesRepository The repository responsible for managing user's favourite catalog items.
 */
class AddFavouriteUseCase @Inject constructor(
    private val favouritesRepository: IFavouritesRepository
) {
    /**
     * Executes the use case to add a catalog item to favourites.
     *
     * @param catalogItemDomain The catalog item to be added to favourites.
     */
    suspend operator fun invoke(
        catalogItemDomain: CatalogItemDomain
    ) = favouritesRepository.addFavourite(catalogItemDomain)
}