package feo.health.datastore.datastore.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import feo.health.common.model.datastore.IpStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val DATASTORE_NAME = "settings"

private val Context.dataStore by preferencesDataStore(
    name = DATASTORE_NAME
)

internal class IpStorageImpl @Inject constructor(
    private val context: Context
): IpStorage {

    companion object {
        private val KEY_IP = stringPreferencesKey("server_ip")
    }

//    override val ip: Flow<String?> = context.dataStore.data
//        .map { prefs -> prefs[KEY_IP] }

    override val ip: String? = "localhost:8080"

    override suspend fun saveIp(value: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_IP] = value
        }
    }
}
