package feo.health.auth.domain.model

import java.time.LocalDate

data class SignUpDomain(
    val name: String,
    val email: String,
    val dateOfBirth: LocalDate,
    val password: String
)
