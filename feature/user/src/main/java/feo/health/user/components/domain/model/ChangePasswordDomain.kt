package feo.health.user.components.domain.model

data class ChangePasswordDomain(
    val oldPassword: String,
    val newPassword: String
)
