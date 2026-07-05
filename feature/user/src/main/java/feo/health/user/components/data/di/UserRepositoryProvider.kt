package feo.health.user.components.data.di

import feo.health.user.components.domain.repository.IFavouritesRepository
import feo.health.user.components.domain.repository.IHistoryRepository
import feo.health.user.components.domain.repository.IUserRepository

/**
 * Provider interface for repository dependencies required by the user feature.
 *
 * This interface is typically implemented by a parent Dagger component (like the App Component)
 * to supply the repository implementations to the user feature module.
 */
interface UserRepositoryProvider {
    /**
     * Provides the [IHistoryRepository] instance.
     *
     * @return The repository managing history records.
     */
    fun historyRepository(): IHistoryRepository

    /**
     * Provides the [IUserRepository] instance.
     *
     * @return The repository managing user profile and session data.
     */
    fun userRepository(): IUserRepository

    /**
     * Provides the [IFavouritesRepository] instance.
     *
     * @return The repository managing user favorites.
     */
    fun favouriteRepository(): IFavouritesRepository
}
