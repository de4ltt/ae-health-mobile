package feo.health.auth.dto.request

import kotlinx.serialization.Serializable

/**
 * Data transfer request object containing authentication credentials.
 *
 * @property email User's account email address.
 * @property password User's account security password.
 */
@Serializable
data class SignInRequest(
    val email: String,
    val password: String
)
