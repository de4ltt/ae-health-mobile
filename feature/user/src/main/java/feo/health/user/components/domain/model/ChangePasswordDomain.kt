package feo.health.user.components.domain.model

/**
 * Domain model representing a request to change the user's password.
 *
 * @property oldPassword The current password of the user.
 * @property newPassword The new password to be set for the user.
 */
data class ChangePasswordDomain(
    val oldPassword: String,
    val newPassword: String
)
