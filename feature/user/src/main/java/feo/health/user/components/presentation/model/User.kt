package feo.health.user.components.presentation.model

import java.time.LocalDate

/**
 * Data class representing a user's profile information.
 *
 * @property name The name of the user.
 * @property email The email address of the user.
 * @property dateOfBirth The date of birth of the user.
 * @property weightKg The weight of the user in kilograms, or null if not set.
 * @property height The height of the user in centimeters, or null if not set.
 */
data class User(
    val name: String,
    val email: String,
    val dateOfBirth: LocalDate,
    val weightKg: Float?,
    val height: Int?
)