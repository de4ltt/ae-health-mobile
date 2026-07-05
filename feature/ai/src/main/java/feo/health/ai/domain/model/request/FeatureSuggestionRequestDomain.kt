package feo.health.ai.domain.model.request

/**
 * Domain request entity containing dynamic user query text input to generate smart recommendations.
 *
 * @property input Raw text input query parameters.
 */
data class FeatureSuggestionRequestDomain(
    val input: String
)
