package feo.health.network.di.module

import dagger.Module
import dagger.Provides
import feo.health.network.di.NetworkModuleScope
import feo.health.network.model.NetworkError
import feo.health.network.model.NetworkException
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

@Module
internal object NetworkModule {

    @NetworkModuleScope
    @Provides
    fun provideHttpClient(): HttpClient =
        HttpClient {
            expectSuccess = false

            install(ContentNegotiation) { json() }

            HttpResponseValidator {
                validateResponse { response ->
                    if (response.status.value >= 400) {
                        val body: String = response.bodyAsText()
                        val apiError = try {
                            Json.decodeFromString<NetworkError>(body)
                        } catch (_: Exception) {
                            throw NetworkException(
                                "Unexpected error: ${response.status}",
                                "fd"
                            )
                        }
                        throw NetworkException("", "")
                    }
                }
            }
        }
}
