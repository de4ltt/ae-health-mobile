package feo.health.catalog.services.dto

import kotlinx.serialization.Serializable

/**
 * Data transfer object mapping medical service procedures details.
 *
 * @property name Service procedure name.
 * @property link Navigational identifier link.
 * @property itemType Visual item styling identifier tag.
 */
@Serializable
data class ServiceDto(
    val name: String,
    val link: String,
    val itemType: String = "service"
)
