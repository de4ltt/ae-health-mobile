package feo.health.ai.di.module

import dagger.Binds
import dagger.Module
import feo.health.ai.api.AIApi
import feo.health.ai.api.IAIApi
import feo.health.ai.di.NetworkAIScope

@Module
internal abstract class AIModule {

    @NetworkAIScope
    @Binds
    abstract fun bindAIApi(aiApi: AIApi): IAIApi

}