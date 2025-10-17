package feo.health.common.model.datastore

import kotlinx.coroutines.flow.Flow

interface IpStorage {
    val ip: String?
    suspend fun saveIp(value: String)
}