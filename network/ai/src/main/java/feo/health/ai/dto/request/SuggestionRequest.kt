package feo.health.ai.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class SuggestionRequest(
    val input: String
)
