package feo.health.ai.data.di.module

import dagger.Module
import dagger.Provides
import feo.health.ai.data.di.FeatureAiScope
import feo.health.ai.domain.use_case.util.IAiUseCases
import feo.health.ai.presentation.viewmodel.AiViewModelFactory

@Module
internal class AiModule {

    @FeatureAiScope
    @Provides
    fun provideAiViewModelFactory(
        aiUseCases: IAiUseCases
    ) = AiViewModelFactory(
        aiUseCases = aiUseCases
    )
}