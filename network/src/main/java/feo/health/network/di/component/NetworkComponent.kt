package feo.health.network.di.component

import dagger.BindsInstance
import dagger.Component
import feo.health.network.datastore.HDataStore
import feo.health.network.di.NetworkModuleScope
import feo.health.network.di.module.NetworkModule
import feo.health.network.refresh_api.IRefreshApi
import io.ktor.client.HttpClient

import feo.health.network.refresh_api.RefreshApiHolder

@NetworkModuleScope
@Component(
    modules = [NetworkModule::class]
)
interface NetworkComponent {

    fun httpClient(): HttpClient
    fun refreshApiHolder(): RefreshApiHolder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindDatastore(dataStore: HDataStore): Builder

        fun build(): NetworkComponent
    }
}