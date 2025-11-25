package feo.health.auth.api

import feo.health.auth.dto.request.SignInRequest
import feo.health.auth.dto.request.SignUpRequest
import feo.health.auth.dto.response.SignInResponse
import feo.health.network.model.NetworkResult

interface IAuthApi {
    suspend fun signIn(signInRequest: SignInRequest): NetworkResult<SignInResponse>
    suspend fun signUp(signUpRequest: SignUpRequest): NetworkResult<Unit>
}