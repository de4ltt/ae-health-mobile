package feo.health.ai.presentation.model.request

/**
 * Presentation request entity containing dynamic user query text input to generate smart recommendations.
 *
 * @property input Raw text input query parameters.
 */
data class FeatureSuggestionRequest(
    val input: String
)
