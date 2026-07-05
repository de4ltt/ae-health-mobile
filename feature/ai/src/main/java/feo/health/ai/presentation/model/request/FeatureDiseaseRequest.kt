package feo.health.ai.presentation.model.request

/**
 * Presentation request entity containing a list of query symptoms to analyze.
 *
 * @property symptoms List of patient physical symptom description strings.
 */
data class FeatureDiseaseRequest(
    val symptoms: List<String>
)
