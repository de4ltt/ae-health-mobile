package feo.health.network.di.component

import dagger.BindsInstance
import dagger.Component
import feo.health.network.datastore.HDataStore
import feo.health.network.di.NetworkModuleScope
import feo.health.network.di.module.NetworkModule
import feo.health.network.refresh_api.IRefreshApi
import io.ktor.client.HttpClient

@NetworkModuleScope
@Component(
    modules = [NetworkModule::class]
)
interface NetworkComponent {

    fun httpClient(): HttpClient

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindRefreshApi(refreshApi: IRefreshApi): Builder

        @BindsInstance
        fun bindDatastore(dataStore: HDataStore): Builder

        fun build(): NetworkComponent
    }
}