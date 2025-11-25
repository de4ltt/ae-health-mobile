package feo.health.user.components.domain.model

import java.time.LocalDate

data class UserDomain(
    val name: String,
    val email: String,
    val dateOfBirth: LocalDate,
    val weightKg: Float?,
    val height: Int?
)