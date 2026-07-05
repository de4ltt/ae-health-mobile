package feo.health.user.components.domain.use_case.favourite

import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.domain.repository.IFavouritesRepository
import javax.inject.Inject

/**
 * Use case for deleting a catalog item from the user's favourites.
 *
 * @property favouritesRepository The repository responsible for managing user's favourite catalog items.
 */
class DeleteFavouriteUseCase @Inject constructor(
    private val favouritesRepository: IFavouritesRepository
) {
    /**
     * Executes the use case to remove a catalog item from favourites.
     *
     * @param catalogItemDomain The catalog item to be removed from favourites.
     */
    suspend operator fun invoke(
        catalogItemDomain: CatalogItemDomain
    ) = favouritesRepository.deleteFavourite(catalogItemDomain)
}