package feo.health.user.components.presentation.model

import java.time.LocalDate

data class User(
    val name: String,
    val email: String,
    val dateOfBirth: LocalDate,
    val weightKg: Float?,
    val height: Int?
)