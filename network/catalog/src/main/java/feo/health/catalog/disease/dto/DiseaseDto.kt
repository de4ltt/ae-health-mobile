package feo.health.catalog.disease.dto

import kotlinx.serialization.Serializable

/**
 * Data transfer object mapping disease details returned by remote endpoints.
 *
 * @property name Disease medical name.
 * @property link Navigational identifier link.
 */
@Serializable
data class DiseaseDto(
    val name: String,
    val link: String
)
