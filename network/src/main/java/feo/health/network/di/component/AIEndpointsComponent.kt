package feo.health.network.di.component

import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import feo.health.common.model.datastore.IpStorage
import feo.health.network.di.NetworkModuleScope
import feo.health.network.di.module.EndpointsModule
import feo.health.network.endpoints.IApiEndpoints
import io.github.cdimascio.dotenv.Dotenv

@NetworkModuleScope
@Component(modules = [EndpointsModule::class])
interface AIEndpointsComponent {

    fun aiEndpoints(): IApiEndpoints.AI

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindIpStorage(ipStorage: IpStorage): Builder

        @BindsInstance
        fun bindDotenv(dotenv: Dotenv): Builder

        fun build(): AIEndpointsComponent
    }
}