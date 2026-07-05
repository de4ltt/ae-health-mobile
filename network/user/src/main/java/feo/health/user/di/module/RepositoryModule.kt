package feo.health.user.di.module

import dagger.Binds
import dagger.Module
import feo.health.user.di.NetworkUserScope
import feo.health.user.components.data.repository.FavouriteRepository
import feo.health.user.components.data.repository.HistoryRepository
import feo.health.user.components.data.repository.UserRepository
import feo.health.user.components.domain.repository.IFavouritesRepository
import feo.health.user.components.domain.repository.IHistoryRepository
import feo.health.user.components.domain.repository.IUserRepository

@Module
internal abstract class RepositoryModule {

    @NetworkUserScope
    @Binds
    abstract fun bindHistoryRepository(historyRepository: HistoryRepository): IHistoryRepository

    @NetworkUserScope
    @Binds
    abstract fun bindUserRepository(userRepository: UserRepository): IUserRepository

    @NetworkUserScope
    @Binds
    abstract fun bindFavouriteRepository(favouriteRepository: FavouriteRepository): IFavouritesRepository

}