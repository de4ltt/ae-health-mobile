package feo.health.ai.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class DiseaseResponse(
    val possibleDiseases: Map<String, Double>,
    val doctors: List<String>,
    val generalResponse: String
)
