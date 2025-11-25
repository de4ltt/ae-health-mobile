package feo.health.user.components.data.di

import dagger.Component
import feo.health.user.components.data.di.module.RepositoryModule
import feo.health.user.components.data.di.module.UseCaseModule
import feo.health.user.components.data.di.module.UserModule
import feo.health.user.components.domain.repository.IFavouritesRepository
import feo.health.user.components.domain.repository.IHistoryRepository
import feo.health.user.components.domain.repository.IUserRepository
import feo.health.user.components.domain.use_case.util.IFavouriteUseCases
import feo.health.user.components.domain.use_case.util.IHistoryUseCases
import feo.health.user.components.domain.use_case.util.IUserUseCases
import feo.health.user.components.presentation.viewmodel.UserViewModelFactory
import feo.health.user.di.DaggerNetworkUserComponent
import feo.health.user.di.NetworkUserComponent

@FeatureUserScope
@Component(
    modules = [UserModule::class, RepositoryModule::class, UseCaseModule::class],
    dependencies = [NetworkUserComponent::class]
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

        fun bindNetworkUserComponent(networkUserComponent: NetworkUserComponent): Builder

        fun build(): FeatureUserComponent
    }
}