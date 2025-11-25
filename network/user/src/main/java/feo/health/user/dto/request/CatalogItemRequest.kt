package feo.health.user.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class CatalogItemRequest(
    val type: String,
    val link: String?
)