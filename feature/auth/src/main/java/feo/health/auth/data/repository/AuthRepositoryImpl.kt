package feo.health.auth.data.repository

import feo.health.auth.domain.model.SignInDomain
import feo.health.auth.domain.model.SignUpDomain
import feo.health.auth.domain.repository.IAuthRepository

class AuthRepositoryImpl: IAuthRepository {

    override suspend fun signIn(signInDomain: SignInDomain) {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(signUpDomain: SignUpDomain) {
        TODO("Not yet implemented")
    }
}