package feo.health.user.components.data.di.module

import dagger.Module
import dagger.Provides
import feo.health.user.components.data.di.FeatureUserScope
import feo.health.user.components.domain.use_case.util.IFavouriteUseCases
import feo.health.user.components.domain.use_case.util.IHistoryUseCases
import feo.health.user.components.domain.use_case.util.IUserUseCases
import feo.health.user.components.presentation.viewmodel.UserViewModelFactory

/**
 * Dagger module providing dependencies for the user feature, specifically presentation-layer factories.
 */
@Module
internal class UserModule {

    /**
     * Provides the [UserViewModelFactory] dependency.
     *
     * @param favouriteUseCases The use cases for managing favorites.
     * @param historyUseCases The use cases for managing history.
     * @param userUseCases The use cases for managing user information and authentication.
     * @return A scoped instance of [UserViewModelFactory].
     */
    @FeatureUserScope
    @Provides
    fun provideSearchViewModelFactory(
        favouriteUseCases: IFavouriteUseCases,
        historyUseCases: IHistoryUseCases,
        userUseCases: IUserUseCases
    ): UserViewModelFactory = UserViewModelFactory(
        favouriteUseCases = favouriteUseCases,
        userUseCases= userUseCases,
        historyUseCases = historyUseCases
    )

}