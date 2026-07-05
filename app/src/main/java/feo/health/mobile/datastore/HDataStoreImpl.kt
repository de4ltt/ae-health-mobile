package feo.health.mobile.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import feo.health.network.datastore.HDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "auth_prefs")

class HDataStoreImpl @Inject constructor(private val context: Context) : HDataStore {

    private object Keys {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val USER_ID = longPreferencesKey("user_id")
    }

    override val accessTokenFlow: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[Keys.ACCESS_TOKEN]
    }

    override val refreshTokenFlow: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[Keys.REFRESH_TOKEN]
    }

    override val userIdFlow: Flow<Long?> = context.dataStore.data.map { prefs ->
        prefs[Keys.USER_ID]
    }

    override suspend fun saveAccessToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[Keys.ACCESS_TOKEN] = token
        }
    }

    override suspend fun saveRefreshToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[Keys.REFRESH_TOKEN] = token
        }
    }

    override suspend fun saveUserId(userId: Long) {
        context.dataStore.edit { prefs ->
            prefs[Keys.USER_ID] = userId
        }
    }

    override suspend fun getAccessToken(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[Keys.ACCESS_TOKEN]
    }

    override suspend fun getRefreshToken(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[Keys.REFRESH_TOKEN]
    }

    override suspend fun getUserId(): Long? {
        val prefs = context.dataStore.data.first()
        return prefs[Keys.USER_ID]
    }

    override suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}
