package feo.health.ai.di.module

import dagger.Binds
import dagger.Module
import feo.health.ai.data.repository.AiRepository
import feo.health.ai.di.NetworkAIScope
import feo.health.ai.domain.repository.IAiRepository

/**
 * Dagger module binding concrete AI repository implementation to its domain contract interface.
 */
@Module
interface AiRepositoryModule {
    /**
     * Binds the AI repository services.
     *
     * @param aiRepository Concrete repository implementation.
     * @return Bounded interface.
     */
    @NetworkAIScope
    @Binds
    fun bindAiRepository(aiRepository: AiRepository): IAiRepository
}