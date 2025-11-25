package feo.health.network.datastore

interface HDataStore {
    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(token: String)
    suspend fun saveUserId(userId: Long)

    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?
    suspend fun getUserId(): Long?

    suspend fun clear()
}