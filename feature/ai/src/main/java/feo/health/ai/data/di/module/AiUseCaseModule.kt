package feo.health.ai.data.di.module

import dagger.Binds
import dagger.Module
import feo.health.ai.data.di.FeatureAiScope
import feo.health.ai.domain.use_case.util.AiUseCases
import feo.health.ai.domain.use_case.util.IAiUseCases

/**
 * Dagger module binding the implementation of [IAiUseCases] utility container to its contract interface.
 */
@Module
abstract class AiUseCaseModule {

    /**
     * Binds the AI presentation use cases wrapper container.
     *
     * @param aiUseCases Concrete repository implementation.
     * @return Bounded interface.
     */
    @Binds
    @FeatureAiScope
    abstract fun bindAiUseCases(aiUseCases: AiUseCases): IAiUseCases
}