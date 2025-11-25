package feo.health.ai.domain.model.response

data class FeatureSuggestionResponseDomain(
    val doctors: List<String>,
    val drugs: List<String>,
    val possibleDiseases: Map<String, Double>,
    val generalAnswer: String
)
