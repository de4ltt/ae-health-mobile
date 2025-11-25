package feo.health.ai.data.di

import dagger.Component
import feo.health.ai.data.di.module.AiModule
import feo.health.ai.data.di.module.AiRepositoryModule
import feo.health.ai.data.di.module.AiUseCaseModule
import feo.health.ai.di.NetworkAIComponent
import feo.health.ai.domain.repository.IAiRepository
import feo.health.ai.domain.use_case.util.IAiUseCases

@FeatureAiScope
@Component(modules = [AiModule::class, AiUseCaseModule::class, AiRepositoryModule::class], dependencies = [NetworkAIComponent::class])
interface FeatureAiComponent {

    fun aiRepository(): IAiRepository

    fun aiUseCases(): IAiUseCases

    @Component.Builder
    interface Builder {

        fun bindNetworkAiComponent(networkAIComponent: NetworkAIComponent): Builder

        fun build(): FeatureAiComponent
    }
}