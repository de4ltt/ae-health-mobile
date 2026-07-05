package feo.health.auth.dto.response

import kotlinx.serialization.Serializable

/**
 * Data transfer response object containing verified access and refresh bearer token values.
 *
 * @property accessToken Authentication bearer token string.
 * @property refreshToken Session refresh token string.
 */
@Serializable
data class SignInResponse(
    val accessToken: String,
    val refreshToken: String
)
