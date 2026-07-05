package feo.health.ai.dto.request

import kotlinx.serialization.Serializable

/**
 * Data transfer request object containing dynamic user query text input to generate smart recommendations.
 *
 * @property input Raw text input query parameters.
 */
@Serializable
data class SuggestionRequest(
    val input: String
)
