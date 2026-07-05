package feo.health.network.di.module

import dagger.Module
import dagger.Provides
import feo.health.network.di.NetworkModuleScope
import kotlinx.serialization.json.Json

/**
 * Dagger module providing standard configured serializer JSON engine instance configuration.
 */
@Module
internal object JsonModule {

    /**
     * Resolves the configured [Json] settings mapper.
     *
     * @return Configured [Json] instance ignoring unknown keys, lenient, and pretty printed.
     */
    @NetworkModuleScope
    @Provides
    fun provideJson(): Json =
        Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        }
}