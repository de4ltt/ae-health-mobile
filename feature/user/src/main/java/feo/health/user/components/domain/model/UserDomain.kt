package feo.health.user.components.domain.model

import java.time.LocalDate

/**
 * Domain model representing user profile information.
 *
 * @property name The name of the user.
 * @property email The email address of the user.
 * @property dateOfBirth The date of birth of the user.
 * @property weightKg The user's weight in kilograms, or null if not provided.
 * @property height The user's height in centimeters, or null if not provided.
 */
data class UserDomain(
    val name: String,
    val email: String,
    val dateOfBirth: LocalDate,
    val weightKg: Float?,
    val height: Int?
)