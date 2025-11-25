package feo.health.user.components.data.di.module

import dagger.Binds
import dagger.Module
import feo.health.user.components.data.di.FeatureUserScope
import feo.health.user.components.domain.use_case.util.IFavouriteUseCases
import feo.health.user.components.domain.use_case.util.IHistoryUseCases
import feo.health.user.components.domain.use_case.util.IUserUseCases
import feo.health.user.components.domain.use_case.util.impl.FavouriteUseCases
import feo.health.user.components.domain.use_case.util.impl.HistoryUseCases
import feo.health.user.components.domain.use_case.util.impl.UserUseCases

@Module
abstract class UseCaseModule {

    @FeatureUserScope
    @Binds
    abstract fun bindHistoryUseCases(historyUseCases: HistoryUseCases): IHistoryUseCases

    @FeatureUserScope
    @Binds
    abstract fun bindUserUseCases(userUseCases: UserUseCases): IUserUseCases

    @FeatureUserScope
    @Binds
    abstract fun bindFavouriteUseCases(favouriteUseCases: FavouriteUseCases): IFavouriteUseCases

}