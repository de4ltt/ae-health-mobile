package feo.health.auth.api

import feo.health.auth.dto.request.SignInRequest
import feo.health.auth.dto.request.SignUpRequest
import feo.health.auth.dto.response.SignInResponse
import feo.health.network.model.NetworkResult

/**
 * API contract interface managing authentication operations.
 */
interface IAuthApi {
    /**
     * Signs in user with credentials.
     *
     * @param signInRequest Credentials request payload.
     * @return [NetworkResult] wrapping response tokens.
     */
    suspend fun signIn(signInRequest: SignInRequest): NetworkResult<SignInResponse>

    /**
     * Registers a new user.
     *
     * @param signUpRequest User registration parameters.
     * @return [NetworkResult] containing operation completion status.
     */
    suspend fun signUp(signUpRequest: SignUpRequest): NetworkResult<Unit>
}