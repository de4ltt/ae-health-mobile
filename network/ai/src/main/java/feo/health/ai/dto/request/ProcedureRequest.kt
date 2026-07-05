package feo.health.ai.dto.request

import kotlinx.serialization.Serializable

/**
 * Data transfer request object querying detailed medical procedurals information.
 *
 * @property serviceName Name of the medical service procedure.
 */
@Serializable
data class ProcedureRequest(
    val serviceName: String
)
