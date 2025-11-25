package feo.health.auth.api

import feo.health.network.endpoints.ApiEndpoints
import feo.health.network.refresh_api.IRefreshApi
import feo.health.network.model.NetworkResult
import feo.health.network.refresh_api.RefreshTokenRequest
import feo.health.network.refresh_api.RefreshTokenResponse
import feo.health.network.util.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

internal class RefreshApi @Inject constructor(
    private val httpClient: HttpClient
) : IRefreshApi {
    override suspend fun refreshToken(refreshTokenRequest: RefreshTokenRequest): NetworkResult<RefreshTokenResponse> =
        RequestHandler.handle {
            httpClient.post(ApiEndpoints.Auth.REFRESH_TOKEN) {
                setBody(refreshTokenRequest)
            }.body()
        }
}