package feo.health.auth.di

import dagger.BindsInstance
import dagger.Component
import feo.health.auth.api.IAuthApi
import feo.health.auth.data.di.AuthRepositoryProvider
import feo.health.auth.di.module.AuthModule
import feo.health.auth.di.module.RepositoryModule
import feo.health.network.datastore.HDataStore
import feo.health.network.di.component.NetworkComponent
import feo.health.network.refresh_api.IRefreshApi

/**
 * Dagger dependency injection component providing authentication APIs and repository dependencies.
 */
@NetworkAuthScope
@Component(modules = [AuthModule::class, RepositoryModule::class], dependencies = [NetworkComponent::class])
interface NetworkAuthComponent : AuthRepositoryProvider {

    /**
     * Resolves the configured [IAuthApi] controller instance.
     *
     * @return The authorization api client.
     */
    fun authApi(): IAuthApi

    /**
     * Resolves the configured token [IRefreshApi] controller instance.
     *
     * @return The token refresh api client.
     */
    fun refreshApi(): IRefreshApi

    /**
     * Component builder contract for assembling the [NetworkAuthComponent].
     */
    @Component.Builder
    interface Builder {

        /**
         * Hooks the core network component dependencies.
         *
         * @param networkComponent Core HTTP configurations provider.
         * @return Dagger builder instance.
         */
        fun bindNetworkComponent(networkComponent: NetworkComponent): Builder

        /**
         * Hooks the local storage manager dependency.
         *
         * @param dataStore The storage controller.
         * @return Dagger builder instance.
         */
        @BindsInstance
        fun bindDatastore(dataStore: HDataStore): Builder

        /**
         * Builds and returns the [NetworkAuthComponent].
         *
         * @return Initialized component instance.
         */
        fun build(): NetworkAuthComponent
    }
}