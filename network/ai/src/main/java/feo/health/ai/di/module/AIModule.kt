package feo.health.ai.di.module

import dagger.Binds
import dagger.Module
import feo.health.ai.api.AIApi
import feo.health.ai.api.IAIApi
import feo.health.ai.di.NetworkAIScope

/**
 * Dagger module binding concrete AI API client implementation to its contract interface.
 */
@Module
internal abstract class AIModule {

    /**
     * Binds the remote AI API client.
     *
     * @param aiApi Concrete api implementation.
     * @return Bounded interface.
     */
    @NetworkAIScope
    @Binds
    abstract fun bindAIApi(aiApi: AIApi): IAIApi
}