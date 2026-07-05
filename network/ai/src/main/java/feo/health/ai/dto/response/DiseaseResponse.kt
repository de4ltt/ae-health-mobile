package feo.health.ai.dto.response

import kotlinx.serialization.Serializable

/**
 * Data transfer response object containing diagnostic results mapped by probability metrics.
 *
 * @property possibleDiseases Map of disease names mapped to probability percentages values.
 * @property doctors Recommended doctor specialties associated with diagnostic matches.
 * @property generalResponse Natural language diagnostic summary output.
 */
@Serializable
data class DiseaseResponse(
    val possibleDiseases: Map<String, Double>,
    val doctors: List<String>,
    val generalResponse: String
)
