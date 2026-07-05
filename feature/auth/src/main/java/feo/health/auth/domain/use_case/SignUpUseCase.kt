package feo.health.auth.domain.use_case

import feo.health.auth.domain.model.SignUpDomain
import feo.health.auth.domain.repository.IAuthRepository
import javax.inject.Inject

/**
 * Use case to register a new user profile account.
 *
 * @property authRepository Authentication services repository client.
 */
class SignUpUseCase @Inject constructor(
    private val authRepository: IAuthRepository
){
    /**
     * Executes the user registration signup request task.
     *
     * @param signUpDomain User registration parameters details.
     */
    suspend operator fun invoke(signUpDomain: SignUpDomain) =
        authRepository.signUp(signUpDomain)
}