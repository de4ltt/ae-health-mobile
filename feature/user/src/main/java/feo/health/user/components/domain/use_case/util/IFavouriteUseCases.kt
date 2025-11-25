package feo.health.user.components.domain.use_case.util

import feo.health.user.components.domain.use_case.favourite.AddFavouriteUseCase
import feo.health.user.components.domain.use_case.favourite.DeleteFavouriteUseCase
import feo.health.user.components.domain.use_case.favourite.GetFavouritesUseCase

interface IFavouriteUseCases {
    val getFavouritesUseCase: GetFavouritesUseCase
    val deleteFavouriteUseCase: DeleteFavouriteUseCase
    val addFavouriteUseCase: AddFavouriteUseCase
}