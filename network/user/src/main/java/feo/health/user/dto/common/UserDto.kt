package feo.health.user.dto.common

import feo.health.network.util.NonNullLocalDateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

/**
 * Data transfer object mapping user account profile parameter settings.
 *
 * @property name User real name.
 * @property email Registered login email address.
 * @property dateOfBirth User date of birth. Serialized using [NonNullLocalDateSerializer].
 * @property weightKg Measured body weight in kilograms, if available.
 * @property height Measured height in centimeters, if available.
 */
@Serializable
data class UserDto(
    val name: String,
    val email: String,
    @Serializable(NonNullLocalDateSerializer::class)
    val dateOfBirth: LocalDate,
    val weightKg: Float? = null,
    val height: Int? = null
)