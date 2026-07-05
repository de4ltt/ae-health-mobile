package feo.health.user.di

import dagger.BindsInstance
import dagger.Component
import feo.health.network.datastore.HDataStore
import feo.health.network.di.component.NetworkComponent
import feo.health.user.api.IUserApi
import feo.health.user.components.data.di.UserRepositoryProvider
import feo.health.user.di.module.RepositoryModule
import feo.health.user.di.module.UserModule
import feo.health.database.di.CoreDatabaseComponent

/**
 * Dagger dependency injection component providing user remote APIs and repository instances.
 */
@NetworkUserScope
@Component(modules = [UserModule::class, RepositoryModule::class], dependencies = [NetworkComponent::class, CoreDatabaseComponent::class])
interface NetworkUserComponent : UserRepositoryProvider {

    /**
     * Resolves the configured [IUserApi] controller instance.
     *
     * @return The user api client.
     */
    fun userApi(): IUserApi

    /**
     * Resolves the cached local credentials store manager.
     *
     * @return Cache datastore implementation.
     */
    fun dataStore(): HDataStore

    /**
     * Component builder contract for assembling the [NetworkUserComponent].
     */
    @Component.Builder
    interface Builder {

        /**
         * Hooks the local storage manager dependency.
         *
         * @param dataStore Local cache credentials store.
         * @return Dagger builder instance.
         */
        @BindsInstance
        fun bindDatastore(dataStore: HDataStore): Builder

        /**
         * Hooks the core network component dependencies.
         *
         * @param networkComponent Core HTTP client configs provider.
         * @return Dagger builder instance.
         */
        fun bindNetworkComponent(networkComponent: NetworkComponent): Builder

        /**
         * Hooks the core local database component dependencies.
         *
         * @param coreDatabaseComponent Local database tables provider.
         * @return Dagger builder instance.
         */
        fun bindCoreDatabaseComponent(coreDatabaseComponent: CoreDatabaseComponent): Builder

        /**
         * Builds and returns the [NetworkUserComponent].
         *
         * @return Initialized component instance.
         */
        fun build(): NetworkUserComponent
    }
}