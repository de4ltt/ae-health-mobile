package feo.health.auth.data.repository

import feo.health.auth.api.IAuthApi
import feo.health.auth.data.mapper.SignInRequestToSignInDomainMapper.toRequest
import feo.health.auth.data.mapper.SignUpRequestToSignUpDomainMapper.toRequest
import feo.health.auth.domain.model.SignInDomain
import feo.health.auth.domain.model.SignUpDomain
import feo.health.auth.domain.repository.IAuthRepository
import feo.health.network.datastore.HDataStore
import feo.health.network.model.NetworkResult
import javax.inject.Inject

/**
 * Repository interface implementation managing credential validation, token saving, and registration requests.
 *
 * @property authApi Remote authentication service endpoints client.
 * @property dataStore Local storage provider used to update token variables.
 */
class AuthRepository @Inject constructor(
    private val authApi: IAuthApi,
    private val dataStore: HDataStore
): IAuthRepository {

    /**
     * Authenticates user using email and password, saving tokens to datastore on success.
     *
     * @param signInDomain Container credentials data.
     * @return `true` if authentication succeeded, `false` otherwise.
     */
    override suspend fun signIn(signInDomain: SignInDomain): Boolean {
        val result = authApi.signIn(signInDomain.toRequest())
        return if (result is NetworkResult.Success) {
            val access = result.data.accessToken
            val refresh = result.data.refreshToken
            dataStore.saveAccessToken(access)
            dataStore.saveRefreshToken(refresh)
            true
        } else false
    }

    /**
     * Triggers a remote sign up registration request.
     *
     * @param signUpDomain Container user registration settings properties.
     */
    override suspend fun signUp(signUpDomain: SignUpDomain) {
        authApi.signUp(signUpDomain.toRequest())
    }
}