package feo.health.ai.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class ProcedureRequest(
    val serviceName: String
)
