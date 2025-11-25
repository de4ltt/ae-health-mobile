package feo.health.user.components.domain.use_case.favourite

import feo.health.user.components.domain.repository.IFavouritesRepository
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(
    private val favouritesRepository: IFavouritesRepository
) {
    suspend operator fun invoke() = favouritesRepository.getFavourites()
}