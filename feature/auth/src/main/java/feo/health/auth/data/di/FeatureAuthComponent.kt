package feo.health.auth.data.di

import dagger.BindsInstance
import dagger.Component
import feo.health.auth.data.di.module.CatalogModule
import feo.health.auth.data.di.module.UseCaseModule
import feo.health.auth.presentation.viewmodel.AuthViewModelFactory
import feo.health.network.datastore.HDataStore

/**
 * Dagger dependency injection component providing login/registration view model factory instances.
 */
@FeatureAuthScope
@Component(
    modules = [CatalogModule::class, UseCaseModule::class],
    dependencies = [AuthRepositoryProvider::class]
)
interface FeatureAuthComponent {

    /**
     * Resolves the configured [AuthViewModelFactory] instance.
     *
     * @return Concrete view model factory instance.
     */
    fun authViewModelFactory(): AuthViewModelFactory

    /**
     * Component builder contract for assembling the [FeatureAuthComponent].
     */
    @Component.Builder
    interface Builder {

        /**
         * Hooks the local credentials cache store dependency.
         *
         * @param dataStore Datastore interface instance.
         * @return Dagger builder instance.
         */
        @BindsInstance
        fun bindDataStore(dataStore: HDataStore): Builder

        /**
         * Hooks the authentication remote repository provider dependency.
         *
         * @param authRepositoryProvider Repository interface instance.
         * @return Dagger builder instance.
         */
        fun bindAuthRepositoryProvider(authRepositoryProvider: AuthRepositoryProvider): Builder

        /**
         * Builds and returns the [FeatureAuthComponent].
         *
         * @return Initialized component instance.
         */
        fun build(): FeatureAuthComponent
    }
}