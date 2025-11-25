package feo.health.ai.data.di.module

import dagger.Binds
import dagger.Module
import feo.health.ai.data.di.FeatureAiScope
import feo.health.ai.domain.use_case.util.AiUseCases
import feo.health.ai.domain.use_case.util.IAiUseCases

@Module
abstract class AiUseCaseModule {

    @Binds
    @FeatureAiScope
    abstract fun bindAiUseCases(aiUseCases: AiUseCases): IAiUseCases
}