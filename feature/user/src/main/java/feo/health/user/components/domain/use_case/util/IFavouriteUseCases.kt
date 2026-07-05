package feo.health.user.components.domain.use_case.util

import feo.health.user.components.domain.use_case.favourite.AddFavouriteUseCase
import feo.health.user.components.domain.use_case.favourite.DeleteFavouriteUseCase
import feo.health.user.components.domain.use_case.favourite.GetFavouritesUseCase

/**
 * Interface representing the wrapper/container for all favourite-related use cases.
 */
interface IFavouriteUseCases {
    /**
     * Use case for retrieving the user's favourite catalog items.
     */
    val getFavouritesUseCase: GetFavouritesUseCase

    /**
     * Use case for deleting a catalog item from favourites.
     */
    val deleteFavouriteUseCase: DeleteFavouriteUseCase

    /**
     * Use case for adding a catalog item to favourites.
     */
    val addFavouriteUseCase: AddFavouriteUseCase
}