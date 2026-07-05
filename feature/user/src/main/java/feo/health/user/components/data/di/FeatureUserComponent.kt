package feo.health.user.components.data.di

import dagger.Component
import feo.health.user.components.data.di.module.UseCaseModule
import feo.health.user.components.data.di.module.UserModule
import feo.health.user.components.domain.repository.IFavouritesRepository
import feo.health.user.components.domain.repository.IHistoryRepository
import feo.health.user.components.domain.repository.IUserRepository
import feo.health.user.components.domain.use_case.util.IFavouriteUseCases
import feo.health.user.components.domain.use_case.util.IHistoryUseCases
import feo.health.user.components.domain.use_case.util.IUserUseCases
import feo.health.user.components.presentation.viewmodel.UserViewModelFactory

@FeatureUserScope
@Component(
    modules = [UserModule::class, UseCaseModule::class],
    dependencies = [UserRepositoryProvider::class]
)
interface FeatureUserComponent {

    fun userViewModelFactory(): UserViewModelFactory

    fun historyRepository(): IHistoryRepository

    fun userRepository(): IUserRepository

    fun favouriteRepository(): IFavouritesRepository

    fun historyUseCases(): IHistoryUseCases

    fun userUseCases(): IUserUseCases

    fun favouriteUseCases(): IFavouriteUseCases

    @Component.Builder
    interface Builder {

        fun bindUserRepositoryProvider(userRepositoryProvider: UserRepositoryProvider): Builder

        fun build(): FeatureUserComponent
    }
}