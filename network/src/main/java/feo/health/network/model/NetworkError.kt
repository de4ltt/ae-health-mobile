package feo.health.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class NetworkError(
    val code: String
)