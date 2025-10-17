package feo.health.network.di.module

import dagger.Module
import dagger.Provides
import feo.health.network.di.NetworkModuleScope
import kotlinx.serialization.json.Json

@Module
internal object JsonModule {

    @NetworkModuleScope
    @Provides
    fun provideJson(): Json =
        Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        }
}