package feo.health.auth.domain.use_case

import feo.health.auth.domain.model.SignInDomain
import feo.health.auth.domain.repository.IAuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: IAuthRepository
){
    suspend operator fun invoke(signInDomain: SignInDomain): Boolean =
        authRepository.signIn(signInDomain)
}