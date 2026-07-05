package feo.health.network.refresh_api

import kotlinx.serialization.Serializable

/**
 * Data transfer object encapsulating the refresh token parameters.
 *
 * @property refreshToken Session refresh token string.
 */
@Serializable
data class RefreshTokenRequest(
    val refreshToken: String
)
