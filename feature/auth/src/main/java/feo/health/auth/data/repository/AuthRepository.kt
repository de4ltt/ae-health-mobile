package feo.health.auth.data.repository

import feo.health.auth.api.IAuthApi
import feo.health.auth.data.mapper.SignInRequestToSignInDomainMapper.toSignInRequest
import feo.health.auth.data.mapper.SignUpRequestToSignUpDomainMapper.toSignUpRequest
import feo.health.auth.domain.model.SignInDomain
import feo.health.auth.domain.model.SignUpDomain
import feo.health.auth.domain.repository.IAuthRepository
import feo.health.network.datastore.HDataStore
import feo.health.network.model.NetworkResult
import feo.health.network.refresh_api.IRefreshApi
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApi: IAuthApi,
    private val dataStore: HDataStore
): IAuthRepository {

    override suspend fun signIn(signInDomain: SignInDomain): Boolean {
        val result = authApi.signIn(signInDomain.toSignInRequest())
        return if (result is NetworkResult.Success) {
            val access = result.data.accessToken
            val refresh = result.data.refreshToken
            dataStore.saveAccessToken(access)
            dataStore.saveRefreshToken(refresh)
            true
        } else false
    }

    override suspend fun signUp(signUpDomain: SignUpDomain) {
        authApi.signUp(signUpDomain.toSignUpRequest())
    }
}