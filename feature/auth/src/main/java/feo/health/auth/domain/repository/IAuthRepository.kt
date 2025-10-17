package feo.health.auth.domain.repository

import feo.health.auth.domain.model.SignInDomain
import feo.health.auth.domain.model.SignUpDomain

interface IAuthRepository {
    suspend fun signIn(signInDomain: SignInDomain)
    suspend fun signUp(signUpDomain: SignUpDomain)
}