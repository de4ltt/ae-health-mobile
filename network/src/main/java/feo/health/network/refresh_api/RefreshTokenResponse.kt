package feo.health.network.refresh_api

import kotlinx.serialization.Serializable

/**
 * Data transfer object encapsulating refreshed bearer and session tokens returned by remote auth API.
 *
 * @property accessToken Authentication bearer token string.
 * @property refreshToken Session refresh token string.
 */
@Serializable
data class RefreshTokenResponse(
    val accessToken: String,
    val refreshToken: String
)
