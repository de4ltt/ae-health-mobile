package feo.health.network.model

import kotlinx.serialization.Serializable

/**
 * Serializable error body model returned by the remote server.
 *
 * @property code Custom application-specific error code string.
 */
@Serializable
internal data class NetworkError(
    val code: String
)