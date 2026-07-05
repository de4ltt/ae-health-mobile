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

/**
 * API implementation managing user authentication sessions.
 * Sends requests to sign in or sign up, and automatically parses/saves tokens and user credentials locally.
 *
 * @property httpClient Network HTTP client provider.
 * @property dataStore Local storage provider used to update token variables.
 */
internal class AuthApi @Inject constructor(
    private val httpClient: HttpClient,
    private val dataStore: HDataStore
) : IAuthApi {

    /**
     * Authenticates credentials, saves tokens and extracted user identifier to datastore,
     * and returns the logged-in session response details.
     *
     * @param signInRequest Contains login credentials.
     * @return [NetworkResult] wrapping the session token details.
     */
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

    /**
     * Registers a new user account profile.
     *
     * @param signUpRequest Contains registration info.
     * @return [NetworkResult] signaling complete status.
     */
    override suspend fun signUp(signUpRequest: SignUpRequest): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.post(ApiEndpoints.Auth.SIGN_UP) {
                setBody(signUpRequest)
            }.body<Unit>()
        }

    /**
     * Decodes and extracts the user identifier claim from the active bearer JWT token string.
     *
     * @param token Authentication bearer JWT string.
     * @return Extracted user id value, or `null` if payload is corrupted or missing "userId" key.
     */
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

    /**
     * Utility performing Base64 decoding mapping rules on JWT token properties.
     *
     * @param input Raw Base64 string from JWT claim segment.
     * @return Decoded readable string content.
     */
    private fun base64UrlDecodeToString(input: String): String {
        var s = input.replace('-', '+').replace('_', '/')
        val pad = s.length % 4
        if (pad != 0) s += "=".repeat(4 - pad)
        val bytes = Base64.getDecoder().decode(s)
        return String(bytes, Charsets.UTF_8)
    }
}