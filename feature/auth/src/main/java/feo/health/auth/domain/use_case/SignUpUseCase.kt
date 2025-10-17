package feo.health.auth.domain.use_case

import feo.health.auth.domain.model.SignUpDomain
import feo.health.auth.domain.repository.IAuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: IAuthRepository
){
    suspend operator fun invoke(signUpDomain: SignUpDomain) =
        authRepository.signUp(signUpDomain)
}