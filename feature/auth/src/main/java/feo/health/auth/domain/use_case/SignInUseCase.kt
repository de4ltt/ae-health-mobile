package feo.health.auth.domain.use_case

import feo.health.auth.domain.model.SignInDomain
import feo.health.auth.domain.repository.IAuthRepository
import javax.inject.Inject

/**
 * Use case to perform a user sign in credentials authentication check.
 *
 * @property authRepository Authentication services repository client.
 */
class SignInUseCase @Inject constructor(
    private val authRepository: IAuthRepository
){
    /**
     * Executes the login authentication verification check task.
     *
     * @param signInDomain Login credentials wrapper details.
     * @return True if credentials matched correctly, false otherwise.
     */
    suspend operator fun invoke(signInDomain: SignInDomain): Boolean =
        authRepository.signIn(signInDomain)
}