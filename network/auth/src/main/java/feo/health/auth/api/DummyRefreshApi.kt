package feo.health.auth.api

import feo.health.network.model.HttpException
import feo.health.network.refresh_api.IRefreshApi
import feo.health.network.model.NetworkResult
import feo.health.network.refresh_api.RefreshTokenRequest
import feo.health.network.refresh_api.RefreshTokenResponse

class DummyRefreshApi: IRefreshApi {
    override suspend fun refreshToken(refreshTokenRequest: RefreshTokenRequest): NetworkResult<RefreshTokenResponse> {
        return NetworkResult.Error(HttpException("Lol..."))
    }
}