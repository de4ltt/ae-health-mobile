package feo.health.ai.data.di

import dagger.Component
import feo.health.ai.data.di.module.AiModule
import feo.health.ai.data.di.module.AiUseCaseModule
import feo.health.ai.domain.repository.IAiRepository
import feo.health.ai.domain.use_case.util.IAiUseCases

/**
 * Dagger dependency injection component providing AI repository and presentation use cases instances.
 */
@FeatureAiScope
@Component(modules = [AiModule::class, AiUseCaseModule::class], dependencies = [AiRepositoryProvider::class])
interface FeatureAiComponent {

    /**
     * Resolves the configured [IAiRepository] instance.
     *
     * @return Concrete repository implementation.
     */
    fun aiRepository(): IAiRepository

    /**
     * Resolves the aggregated [IAiUseCases] utility container.
     *
     * @return AI presentation use cases wrapper.
     */
    fun aiUseCases(): IAiUseCases

    /**
     * Component builder contract for assembling the [FeatureAiComponent].
     */
    @Component.Builder
    interface Builder {

        /**
         * Hooks the remote AI repository provider dependency.
         *
         * @param aiRepositoryProvider Concrete repository interface instance.
         * @return Dagger builder instance.
         */
        fun bindAiRepositoryProvider(aiRepositoryProvider: AiRepositoryProvider): Builder

        /**
         * Builds and returns the [FeatureAiComponent].
         *
         * @return Initialized component instance.
         */
        fun build(): FeatureAiComponent
    }
}