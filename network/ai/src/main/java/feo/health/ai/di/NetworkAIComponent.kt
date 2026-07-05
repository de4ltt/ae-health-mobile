package feo.health.ai.di

import dagger.BindsInstance
import dagger.Component
import feo.health.ai.api.IAIApi
import feo.health.ai.di.module.AIModule
import feo.health.network.di.component.NetworkComponent

import feo.health.ai.data.di.AiRepositoryProvider
import feo.health.ai.di.module.AiRepositoryModule

@NetworkAIScope
@Component(modules = [AIModule::class, AiRepositoryModule::class], dependencies = [NetworkComponent::class])
interface NetworkAIComponent : AiRepositoryProvider {

    fun aiApi(): IAIApi

    @Component.Builder
    interface Builder {

        fun bindNetworkComponent(networkComponent: NetworkComponent): Builder

        fun build(): NetworkAIComponent
    }
}