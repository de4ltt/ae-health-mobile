package feo.health.user.components.data.di.module

import dagger.Module
import dagger.Provides
import feo.health.user.components.data.di.FeatureUserScope
import feo.health.user.components.domain.use_case.util.IFavouriteUseCases
import feo.health.user.components.domain.use_case.util.IHistoryUseCases
import feo.health.user.components.domain.use_case.util.IUserUseCases
import feo.health.user.components.presentation.viewmodel.UserViewModelFactory

@Module
internal class UserModule {

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