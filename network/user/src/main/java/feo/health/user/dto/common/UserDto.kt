package feo.health.user.dto.common

import feo.health.network.util.LocalDateSerializer
import feo.health.network.util.NonNullLocalDateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class UserDto(
    val name: String,
    val email: String,
    @Serializable(NonNullLocalDateSerializer::class)
    val dateOfBirth: LocalDate,
    val weightKg: Float? = null,
    val height: Int? = null
)