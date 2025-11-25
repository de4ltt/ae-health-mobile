package feo.health.network.refresh_api

import feo.health.network.model.NetworkResult

interface IRefreshApi {
    suspend fun refreshToken(refreshTokenRequest: RefreshTokenRequest): NetworkResult<RefreshTokenResponse>
}