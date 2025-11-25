package feo.health.network.di.module

import dagger.Module
import dagger.Provides
import feo.health.network.datastore.HDataStore
import feo.health.network.di.NetworkModuleScope
import feo.health.network.refresh_api.IRefreshApi
import feo.health.network.model.NetworkException
import feo.health.network.model.NetworkResult
import feo.health.network.refresh_api.RefreshTokenRequest
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.statement.bodyAsText
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

@Module
internal object NetworkModule {

    @NetworkModuleScope
    @Provides
    fun provideHttpClient(dataStore: HDataStore, refreshApi: IRefreshApi): HttpClient =
        HttpClient {
            expectSuccess = true

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 10000
            }

            install(DefaultRequest) {
                headers.append("X-User-Id", runBlocking { "${dataStore.getUserId() ?: ""}" })
                headers.append(
                    "Authorization",
                    runBlocking { "Bearer ${dataStore.getAccessToken() ?: ""}" })
                headers.append("Content-Type", "Application/Json")
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("HTTP LOG: $message")
                    }
                }
                level = LogLevel.ALL
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        val accessToken = dataStore.getAccessToken()
                        val refreshToken = dataStore.getRefreshToken()
                        if (accessToken != null && refreshToken != null) {
                            BearerTokens(accessToken, refreshToken)
                        } else null
                    }
                    refreshTokens {
                        val refreshToken = dataStore.getRefreshToken()
                        if (refreshToken != null) {
                            val result = refreshApi.refreshToken(RefreshTokenRequest(refreshToken))
                            when (result) {
                                is NetworkResult.Success -> {
                                    val response = result.data
                                    dataStore.saveAccessToken(response.accessToken)
                                    dataStore.saveRefreshToken(response.refreshToken)
                                    BearerTokens(response.accessToken, response.refreshToken)
                                }
                                is NetworkResult.Error -> {
                                    dataStore.clear()
                                    null
                                }
                            }
                        } else {
                            null
                        }
                    }
                }
            }

            HttpResponseValidator {
                validateResponse { response ->
                    if (response.status.value >= 400) {
                        val body: String = response.bodyAsText()
                        throw NetworkException(body, response.status.value.toString())
                    }
                }
            }
        }
}
