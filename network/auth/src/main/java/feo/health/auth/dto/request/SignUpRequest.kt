package feo.health.auth.dto.request

import feo.health.network.util.NonNullLocalDateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

/**
 * Data transfer request object containing registration parameters to create a new profile.
 *
 * @property name Account user real full name.
 * @property email Account login email address.
 * @property dateOfBirth User date of birth. Serialized using [NonNullLocalDateSerializer].
 * @property password Account login security password.
 */
@Serializable
data class SignUpRequest(
    val name: String,
    val email: String,
    @Serializable(NonNullLocalDateSerializer::class)
    val dateOfBirth: LocalDate,
    val password: String
)
