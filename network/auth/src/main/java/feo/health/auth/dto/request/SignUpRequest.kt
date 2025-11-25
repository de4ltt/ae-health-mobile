package feo.health.auth.dto.request

import feo.health.network.util.NonNullLocalDateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class SignUpRequest(
    val name: String,
    val email: String,
    @Serializable(NonNullLocalDateSerializer::class)
    val dateOfBirth: LocalDate,
    val password: String
)
