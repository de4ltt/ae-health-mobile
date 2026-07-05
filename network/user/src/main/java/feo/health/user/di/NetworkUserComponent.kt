package feo.health.user.di

import dagger.BindsInstance
import dagger.Component
import feo.health.network.datastore.HDataStore
import feo.health.network.di.component.NetworkComponent
import feo.health.user.api.IUserApi
import feo.health.user.di.module.UserModule

import feo.health.user.components.data.di.UserRepositoryProvider
import feo.health.user.di.module.RepositoryModule

@NetworkUserScope
@Component(modules = [UserModule::class, RepositoryModule::class], dependencies = [NetworkComponent::class])
interface NetworkUserComponent : UserRepositoryProvider {

    fun userApi(): IUserApi

    fun dataStore(): HDataStore

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindDatastore(dataStore: HDataStore): Builder

        fun bindNetworkComponent(networkComponent: NetworkComponent): Builder

        fun build(): NetworkUserComponent
    }
}