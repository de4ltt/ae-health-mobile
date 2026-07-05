package feo.health.ai.dto.request

import kotlinx.serialization.Serializable

/**
 * Data transfer request object containing a list of query symptoms to analyze.
 *
 * @property symptoms List of patient physical symptom description strings.
 */
@Serializable
data class DiseaseRequest(
    val symptoms: List<String>
)
