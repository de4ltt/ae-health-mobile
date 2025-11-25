package feo.health.user.components.data.di.module

import dagger.Binds
import dagger.Module
import feo.health.user.components.data.di.FeatureUserScope
import feo.health.user.components.data.repository.FavouriteRepository
import feo.health.user.components.data.repository.HistoryRepository
import feo.health.user.components.data.repository.UserRepository
import feo.health.user.components.domain.repository.IFavouritesRepository
import feo.health.user.components.domain.repository.IHistoryRepository
import feo.health.user.components.domain.repository.IUserRepository

@Module
internal abstract class RepositoryModule {

    @FeatureUserScope
    @Binds
    abstract fun bindHistoryRepository(historyRepository: HistoryRepository): IHistoryRepository

    @FeatureUserScope
    @Binds
    abstract fun bindUserRepository(userRepository: UserRepository): IUserRepository

    @FeatureUserScope
    @Binds
    abstract fun bindFavouriteRepository(favouriteRepository: FavouriteRepository): IFavouritesRepository

}