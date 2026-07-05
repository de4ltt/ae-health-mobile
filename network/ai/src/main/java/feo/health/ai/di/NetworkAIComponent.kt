package feo.health.ai.di

import dagger.Component
import feo.health.ai.api.IAIApi
import feo.health.ai.data.di.AiRepositoryProvider
import feo.health.ai.di.module.AIModule
import feo.health.ai.di.module.AiRepositoryModule
import feo.health.network.di.component.NetworkComponent

/**
 * Dagger dependency injection component providing AI APIs and repository instances.
 */
@NetworkAIScope
@Component(modules = [AIModule::class, AiRepositoryModule::class], dependencies = [NetworkComponent::class])
interface NetworkAIComponent : AiRepositoryProvider {

    /**
     * Resolves the configured [IAIApi] remote service client instance.
     *
     * @return The AI API client.
     */
    fun aiApi(): IAIApi

    /**
     * Component builder contract for assembling the [NetworkAIComponent].
     */
    @Component.Builder
    interface Builder {

        /**
         * Hooks the core network component dependencies.
         *
         * @param networkComponent Core HTTP client configs provider.
         * @return Dagger builder instance.
         */
        fun bindNetworkComponent(networkComponent: NetworkComponent): Builder

        /**
         * Builds and returns the [NetworkAIComponent].
         *
         * @return Initialized component instance.
         */
        fun build(): NetworkAIComponent
    }
}