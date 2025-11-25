package feo.health.auth.api

import feo.health.auth.dto.request.SignInRequest
import feo.health.auth.dto.request.SignUpRequest
import feo.health.auth.dto.response.SignInResponse
import feo.health.network.datastore.HDataStore
import feo.health.network.endpoints.ApiEndpoints
import feo.health.network.model.NetworkResult
import feo.health.network.util.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.util.Base64
import javax.inject.Inject

internal class AuthApi @Inject constructor(
    private val httpClient: HttpClient,
    private val dataStore: HDataStore
) : IAuthApi {

    override suspend fun signIn(signInRequest: SignInRequest): NetworkResult<SignInResponse> =
        RequestHandler.handle {
            val result = httpClient.post(ApiEndpoints.Auth.SIGN_IN) {
                setBody<SignInRequest>(signInRequest)
            }.body<SignInResponse>()

            val access = result.accessToken
            val refresh = result.refreshToken

            dataStore.saveAccessToken(access)
            dataStore.saveRefreshToken(refresh)

            extractJwtUserIdClaim(token = access)?.let {
                dataStore.saveUserId(it.toLong())
            }

            result
        }

    override suspend fun signUp(signUpRequest: SignUpRequest): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.post(ApiEndpoints.Auth.SIGN_UP) {
                setBody(signUpRequest)
            }.body<Unit>()
        }

    private fun extractJwtUserIdClaim(token: String): String? {
        val parts = token.split('.')
        if (parts.size < 2) return null
        val payload = parts[1]
        val json = try {
            val decoded = base64UrlDecodeToString(payload)
            Json.parseToJsonElement(decoded).jsonObject
        } catch (e: Exception) {
            return null
        }
        return json["userId"]?.jsonPrimitive?.contentOrNull
    }

    private fun base64UrlDecodeToString(input: String): String {
        var s = input.replace('-', '+').replace('_', '/')
        val pad = s.length % 4
        if (pad != 0) s += "=".repeat(4 - pad)
        val bytes = Base64.getDecoder().decode(s)
        return String(bytes, Charsets.UTF_8)
    }
}