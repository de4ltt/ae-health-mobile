package feo.health.network.refresh_api

import feo.health.network.model.NetworkResult

/**
 * API contract handling access/refresh token updates.
 */
interface IRefreshApi {
    /**
     * Sends a network call to refresh the access token.
     *
     * @param refreshTokenRequest Contains the active session refresh token.
     * @return Result containing access and refresh tokens response or network failure details.
     */
    suspend fun refreshToken(refreshTokenRequest: RefreshTokenRequest): NetworkResult<RefreshTokenResponse>
}