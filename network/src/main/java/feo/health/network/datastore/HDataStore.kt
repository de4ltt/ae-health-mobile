package feo.health.network.datastore

import kotlinx.coroutines.flow.Flow

/**
 * Local key-value storage contract used to store, retrieve, and clear authentication tokens
 * and user profile identifiers.
 */
interface HDataStore {
    /**
     * Flow tracking active access token changes.
     */
    val accessTokenFlow: Flow<String?>

    /**
     * Flow tracking active refresh token changes.
     */
    val refreshTokenFlow: Flow<String?>

    /**
     * Flow tracking active user account ID changes.
     */
    val userIdFlow: Flow<Long?>

    /**
     * Saves the access token to persistence.
     *
     * @param token Authentication bearer token string.
     */
    suspend fun saveAccessToken(token: String)

    /**
     * Saves the refresh token to persistence.
     *
     * @param token Session refresh token string.
     */
    suspend fun saveRefreshToken(token: String)

    /**
     * Saves the logged-in user identifier.
     *
     * @param userId The unique user id.
     */
    suspend fun saveUserId(userId: Long)

    /**
     * Synchronously fetches the current active access token.
     *
     * @return The active access token, or `null` if unauthorized.
     */
    suspend fun getAccessToken(): String?

    /**
     * Synchronously fetches the session refresh token.
     *
     * @return The active refresh token, or `null` if expired.
     */
    suspend fun getRefreshToken(): String?

    /**
     * Synchronously fetches the unique user identifier.
     *
     * @return The active user id, or `null` if logged out.
     */
    suspend fun getUserId(): Long?

    /**
     * Clears all stored tokens and user identifier properties.
     */
    suspend fun clear()
}