package feo.health.network.di.component

import dagger.BindsInstance
import dagger.Component
import feo.health.common.model.datastore.IpStorage
import feo.health.network.di.NetworkModuleScope
import feo.health.network.di.module.NetworkModule
import io.github.cdimascio.dotenv.Dotenv
import io.ktor.client.HttpClient

@NetworkModuleScope
@Component(
    modules = [NetworkModule::class]
)
interface NetworkComponent {

    fun httpClient(): HttpClient

    fun dotenv(): Dotenv

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindDotenv(dotenv: Dotenv): Builder

        @BindsInstance
        fun bindIpStorage(ipStorage: IpStorage): Builder

        fun build(): NetworkComponent
    }
}