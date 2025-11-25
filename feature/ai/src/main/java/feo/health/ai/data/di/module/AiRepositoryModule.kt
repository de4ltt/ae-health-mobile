package feo.health.ai.data.di.module

import dagger.Binds
import dagger.Module
import feo.health.ai.data.repository.AiRepository
import feo.health.ai.domain.repository.IAiRepository

@Module
interface AiRepositoryModule {
    @Binds
    fun bindAiRepository(aiRepository: AiRepository): IAiRepository
}