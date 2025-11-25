package feo.health.ai.di

import dagger.BindsInstance
import dagger.Component
import feo.health.ai.api.IAIApi
import feo.health.ai.di.module.AIModule
import feo.health.network.di.component.NetworkComponent

@NetworkAIScope
@Component(modules = [AIModule::class], dependencies = [NetworkComponent::class])
interface NetworkAIComponent {

    fun aiApi(): IAIApi

    @Component.Builder
    interface Builder {

        fun bindNetworkComponent(networkComponent: NetworkComponent): Builder

        fun build(): NetworkAIComponent
    }
}