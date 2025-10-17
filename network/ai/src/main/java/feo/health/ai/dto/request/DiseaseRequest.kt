package feo.health.ai.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class DiseaseRequest(
    val symptoms: List<String>
)
