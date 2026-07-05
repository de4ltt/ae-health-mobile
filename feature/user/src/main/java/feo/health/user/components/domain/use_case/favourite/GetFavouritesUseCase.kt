package feo.health.user.components.domain.use_case.favourite

import feo.health.user.components.domain.repository.IFavouritesRepository
import javax.inject.Inject

/**
 * Use case for retrieving the user's favourite catalog items.
 *
 * @property favouritesRepository The repository responsible for managing user's favourite catalog items.
 */
class GetFavouritesUseCase @Inject constructor(
    private val favouritesRepository: IFavouritesRepository
) {
    /**
     * Executes the use case to retrieve the favourites list.
     *
     * @return A map of categories to list of favourite catalog items.
     */
    suspend operator fun invoke() = favouritesRepository.getFavourites()
}