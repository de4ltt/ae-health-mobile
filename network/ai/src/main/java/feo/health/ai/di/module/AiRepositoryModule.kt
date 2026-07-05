package feo.health.ai.di.module

import dagger.Binds
import dagger.Module
import feo.health.ai.data.repository.AiRepository
import feo.health.ai.domain.repository.IAiRepository
import feo.health.ai.di.NetworkAIScope

@Module
interface AiRepositoryModule {
    @NetworkAIScope
    @Binds
    fun bindAiRepository(aiRepository: AiRepository): IAiRepository
}