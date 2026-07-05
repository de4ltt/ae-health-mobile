package feo.health.user.di.module

import dagger.Binds
import dagger.Module
import feo.health.user.components.data.repository.FavouriteRepository
import feo.health.user.components.data.repository.HistoryRepository
import feo.health.user.components.data.repository.UserRepository
import feo.health.user.components.domain.repository.IFavouritesRepository
import feo.health.user.components.domain.repository.IHistoryRepository
import feo.health.user.components.domain.repository.IUserRepository
import feo.health.user.di.NetworkUserScope

/**
 * Dagger module binding concrete user repository implementations to their domain contract interfaces.
 */
@Module
internal abstract class RepositoryModule {

    /**
     * Binds the user interaction history repository.
     *
     * @param historyRepository Concrete repository implementation.
     * @return Bounded interface.
     */
    @NetworkUserScope
    @Binds
    abstract fun bindHistoryRepository(historyRepository: HistoryRepository): IHistoryRepository

    /**
     * Binds the user profile settings repository.
     *
     * @param userRepository Concrete repository implementation.
     * @return Bounded interface.
     */
    @NetworkUserScope
    @Binds
    abstract fun bindUserRepository(userRepository: UserRepository): IUserRepository

    /**
     * Binds the user bookmarked favourites repository.
     *
     * @param favouriteRepository Concrete repository implementation.
     * @return Bounded interface.
     */
    @NetworkUserScope
    @Binds
    abstract fun bindFavouriteRepository(favouriteRepository: FavouriteRepository): IFavouritesRepository
}