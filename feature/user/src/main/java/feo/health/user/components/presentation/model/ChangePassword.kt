package feo.health.user.components.presentation.model

/**
 * Data class representing the model for changing the user's password.
 *
 * @property oldPassword The user's current password.
 * @property newPassword The new password the user wants to set.
 */
data class ChangePassword(
    val oldPassword: String,
    val newPassword: String
)
