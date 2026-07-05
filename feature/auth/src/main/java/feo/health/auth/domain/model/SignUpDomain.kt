package feo.health.auth.domain.model

import java.time.LocalDate

/**
 * Domain entity holding demographic credentials required to register a new user profile account.
 *
 * @property name User full name string.
 * @property email Input user email account address.
 * @property dateOfBirth Date of birth details representation.
 * @property password User secret password string key.
 */
data class SignUpDomain(
    val name: String,
    val email: String,
    val dateOfBirth: LocalDate,
    val password: String
)
