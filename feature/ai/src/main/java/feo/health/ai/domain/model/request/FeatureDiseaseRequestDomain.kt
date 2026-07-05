package feo.health.ai.domain.model.request

/**
 * Domain request entity containing a list of query symptoms to analyze.
 *
 * @property symptoms List of patient physical symptom description strings.
 */
data class FeatureDiseaseRequestDomain(
    val symptoms: List<String>
)
