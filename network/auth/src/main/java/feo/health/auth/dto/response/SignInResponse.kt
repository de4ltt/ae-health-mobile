package feo.health.auth.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    val accessToken: String,
    val refreshToken: String
)
