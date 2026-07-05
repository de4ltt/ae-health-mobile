package feo.health.network.di.component

import dagger.BindsInstance
import dagger.Component
import feo.health.network.datastore.HDataStore
import feo.health.network.di.NetworkModuleScope
import feo.health.network.di.module.NetworkModule
import feo.health.network.refresh_api.RefreshApiHolder
import io.ktor.client.HttpClient

/**
 * Dagger DI component serving as the central point for HTTP client instantiation and datastore injection.
 */
@NetworkModuleScope
@Component(
    modules = [NetworkModule::class]
)
interface NetworkComponent {

    /**
     * Resolves the configured shared HTTP client.
     *
     * @return Configured Ktor [HttpClient] instance.
     */
    fun httpClient(): HttpClient

    /**
     * Resolves the shared helper holder instance managing dynamic session refresh APIs.
     *
     * @return The active [RefreshApiHolder] singleton instance.
     */
    fun refreshApiHolder(): RefreshApiHolder

    /**
     * Component builder contract for assembling the [NetworkComponent].
     */
    @Component.Builder
    interface Builder {

        /**
         * Binds the local datastore dependency to the network graph.
         *
         * @param dataStore The local storage manager.
         * @return The Dagger [Builder] instance.
         */
        @BindsInstance
        fun bindDatastore(dataStore: HDataStore): Builder

        /**
         * Binds the local cache directory file dependency to the network graph.
         *
         * @param cacheDir The JVM local directory to write cache files.
         * @return The Dagger [Builder] instance.
         */
        @BindsInstance
        fun bindCacheDir(cacheDir: java.io.File): Builder

        /**
         * Assembles and returns the [NetworkComponent].
         *
         * @return Resolved component instance.
         */
        fun build(): NetworkComponent
    }
}