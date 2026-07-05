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

/**
 * Dagger module responsible for binding use case implementations to their interfaces.
 */
@Module
abstract class UseCaseModule {

    /**
     * Binds the [HistoryUseCases] implementation to the [IHistoryUseCases] interface.
     *
     * @param historyUseCases The concrete implementation of history use cases.
     * @return The history use cases interface bound in the graph.
     */
    @FeatureUserScope
    @Binds
    abstract fun bindHistoryUseCases(historyUseCases: HistoryUseCases): IHistoryUseCases

    /**
     * Binds the [UserUseCases] implementation to the [IUserUseCases] interface.
     *
     * @param userUseCases The concrete implementation of user use cases.
     * @return The user use cases interface bound in the graph.
     */
    @FeatureUserScope
    @Binds
    abstract fun bindUserUseCases(userUseCases: UserUseCases): IUserUseCases

    /**
     * Binds the [FavouriteUseCases] implementation to the [IFavouriteUseCases] interface.
     *
     * @param favouriteUseCases The concrete implementation of favourite use cases.
     * @return The favourite use cases interface bound in the graph.
     */
    @FeatureUserScope
    @Binds
    abstract fun bindFavouriteUseCases(favouriteUseCases: FavouriteUseCases): IFavouriteUseCases

}