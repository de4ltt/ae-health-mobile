package feo.health.ai.data.di.module

import dagger.Module
import dagger.Provides
import feo.health.ai.data.di.FeatureAiScope
import feo.health.ai.domain.use_case.util.IAiUseCases
import feo.health.ai.presentation.viewmodel.AiViewModelFactory

/**
 * Dagger module providing presentation layer factory dependencies for AI features.
 */
@Module
internal class AiModule {

    /**
     * Provides the factory instance required to construct [AiViewModel].
     *
     * @param aiUseCases The presentation use cases bundle.
     * @return Concrete view model factory instance.
     */
    @FeatureAiScope
    @Provides
    fun provideAiViewModelFactory(
        aiUseCases: IAiUseCases
    ) = AiViewModelFactory(
        aiUseCases = aiUseCases
    )
}