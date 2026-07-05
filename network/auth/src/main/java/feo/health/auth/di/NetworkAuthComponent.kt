package feo.health.auth.di

import dagger.BindsInstance
import dagger.Component
import feo.health.auth.api.IAuthApi
import feo.health.auth.di.module.AuthModule
import feo.health.network.datastore.HDataStore
import feo.health.network.di.component.NetworkComponent
import feo.health.network.refresh_api.IRefreshApi
import io.ktor.client.HttpClient

import feo.health.auth.data.di.AuthRepositoryProvider
import feo.health.auth.di.module.RepositoryModule

@NetworkAuthScope
@Component(modules = [AuthModule::class, RepositoryModule::class], dependencies = [NetworkComponent::class])
interface NetworkAuthComponent : AuthRepositoryProvider {

    fun authApi(): IAuthApi

    fun refreshApi(): IRefreshApi

    @Component.Builder
    interface Builder {

        fun bindNetworkComponent(networkComponent: NetworkComponent): Builder

        @BindsInstance
        fun bindDatastore(dataStore: HDataStore): Builder

        fun build(): NetworkAuthComponent
    }
}