package feo.health.user.dto.request

import kotlinx.serialization.Serializable

/**
 * Data transfer request object containing previous and updated user password parameters.
 *
 * @property oldPassword Active security password validation key.
 * @property newPassword Updated replacement security password key.
 */
@Serializable
data class ChangePasswordRequest(
    val oldPassword: String,
    val newPassword: String
)
