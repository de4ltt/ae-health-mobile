package feo.health.secrets.di

import dagger.BindsInstance
import dagger.Component
import feo.health.secrets.HSecrets

/**
 * Dagger component that provides dependency injection for application secrets ([HSecrets]).
 */
@CoreSecretsScope
@Component
interface CoreSecretsComponent {

    /**
     * Exposes the injected secrets container instance.
     */
    fun secrets(): HSecrets

    /**
     * Builder factory interface for [CoreSecretsComponent].
     */
    @Component.Builder
    interface Builder {

        /**
         * Binds a concrete implementation of [HSecrets] into the dependency graph.
         */
        @BindsInstance
        fun bindSecrets(secrets: HSecrets): Builder

        /**
         * Builds and returns an initialized [CoreSecretsComponent].
         */
        fun build(): CoreSecretsComponent
    }
}