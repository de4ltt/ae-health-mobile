package feo.health.ai.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class SuggestionResponse(
    val doctors: List<String>,
    val drugs: List<String>,
    val possibleDiseases: Map<String, Double>,
    val generalAnswer: String
)
