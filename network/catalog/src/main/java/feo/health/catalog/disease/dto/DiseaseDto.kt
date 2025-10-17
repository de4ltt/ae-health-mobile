package feo.health.catalog.disease.dto

import kotlinx.serialization.Serializable

@Serializable
data class DiseaseDto(
    val name: String,
    val link: String
)
