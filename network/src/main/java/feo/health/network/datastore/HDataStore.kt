package feo.health.network.datastore

import kotlinx.coroutines.flow.Flow

interface HDataStore {
    val accessTokenFlow: Flow<String?>
    val refreshTokenFlow: Flow<String?>
    val userIdFlow: Flow<Long?>

    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(token: String)
    suspend fun saveUserId(userId: Long)

    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?
    suspend fun getUserId(): Long?

    suspend fun clear()
}