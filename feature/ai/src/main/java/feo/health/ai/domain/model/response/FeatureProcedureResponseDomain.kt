package feo.health.ai.domain.model.response

data class FeatureProcedureResponseDomain(
    val name: String,
    val description: String,
    val contradictions: List<String>,
    val indications: List<String>
)
