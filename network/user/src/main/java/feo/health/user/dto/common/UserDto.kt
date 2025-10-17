package feo.health.user.dto.common

import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class UserDto(
    val name: String,
    val email: String,
    val dateOfBirth: LocalDate,
    val weightKg: Float?,
    val height: Int?
)