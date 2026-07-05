package feo.health.auth.domain.repository

import feo.health.auth.domain.model.SignInDomain
import feo.health.auth.domain.model.SignUpDomain

/**
 * Repository interface contract managing authentication remote api requests.
 */
interface IAuthRepository {
    /**
     * Authenticates a user using email and password details.
     *
     * @param signInDomain Sign in login credentials.
     * @return True if authentication succeeds, false otherwise.
     */
    suspend fun signIn(signInDomain: SignInDomain): Boolean

    /**
     * Registers a new user account profile using details.
     *
     * @param signUpDomain Registration credential specifications.
     */
    suspend fun signUp(signUpDomain: SignUpDomain)
}