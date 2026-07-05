package feo.health.ai.domain.model.response

/**
 * Domain response entity containing diagnostic results mapped by probability metrics.
 *
 * @property possibleDiseases Map of disease names mapped to probability percentages values.
 * @property doctors Recommended doctor specialties associated with diagnostic matches.
 * @property generalResponse Natural language diagnostic summary output.
 */
data class FeatureDiseaseResponseDomain(
    val possibleDiseases: Map<String, Double>,
    val doctors: List<String>,
    val generalResponse: String
)
