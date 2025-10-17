package feo.health.ai.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ProcedureResponse(
    val name: String,
    val description: String,
    val contradictions: List<String>,
    val indications: List<String>
)
