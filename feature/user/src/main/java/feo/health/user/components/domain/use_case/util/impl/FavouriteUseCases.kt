package feo.health.user.components.domain.use_case.util.impl

import feo.health.user.components.domain.use_case.favourite.AddFavouriteUseCase
import feo.health.user.components.domain.use_case.favourite.DeleteFavouriteUseCase
import feo.health.user.components.domain.use_case.favourite.GetFavouritesUseCase
import feo.health.user.components.domain.use_case.util.IFavouriteUseCases
import javax.inject.Inject

/**
 * Implementation of [IFavouriteUseCases] containing all favourite-related use cases.
 *
 * @property getFavouritesUseCase Use case for retrieving the user's favourite catalog items.
 * @property deleteFavouriteUseCase Use case for deleting a catalog item from favourites.
 * @property addFavouriteUseCase Use case for adding a catalog item to favourites.
 */
data class FavouriteUseCases @Inject constructor(
    override val getFavouritesUseCase: GetFavouritesUseCase,
    override val deleteFavouriteUseCase: DeleteFavouriteUseCase,
    override val addFavouriteUseCase: AddFavouriteUseCase
): IFavouriteUseCases
