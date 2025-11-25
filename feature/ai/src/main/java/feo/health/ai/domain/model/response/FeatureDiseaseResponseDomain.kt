package feo.health.ai.domain.model.response

data class FeatureDiseaseResponseDomain(
    val possibleDiseases: Map<String, Double>,
    val doctors: List<String>,
    val generalResponse: String
)
