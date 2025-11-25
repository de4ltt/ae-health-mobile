package feo.health.user.components.domain.use_case.util.impl

import feo.health.user.components.domain.use_case.favourite.AddFavouriteUseCase
import feo.health.user.components.domain.use_case.favourite.DeleteFavouriteUseCase
import feo.health.user.components.domain.use_case.favourite.GetFavouritesUseCase
import feo.health.user.components.domain.use_case.util.IFavouriteUseCases
import javax.inject.Inject

data class FavouriteUseCases @Inject constructor(
    override val getFavouritesUseCase: GetFavouritesUseCase,
    override val deleteFavouriteUseCase: DeleteFavouriteUseCase,
    override val addFavouriteUseCase: AddFavouriteUseCase
): IFavouriteUseCases
