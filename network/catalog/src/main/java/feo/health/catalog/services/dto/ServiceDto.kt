package feo.health.catalog.services.dto

import kotlinx.serialization.Serializable

@Serializable
data class ServiceDto(
    val name: String,
    val link: String,
    val itemType: String = "service"
)
