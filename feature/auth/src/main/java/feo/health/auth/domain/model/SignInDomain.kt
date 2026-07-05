package feo.health.auth.domain.model

/**
 * Domain entity holding credentials required to perform a user login authentication check.
 *
 * @property email Input user email account address.
 * @property password User secret password string key.
 */
data class SignInDomain(
    val email: String,
    val password: String
)