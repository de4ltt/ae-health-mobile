package feo.health.user.dto.response

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class CatalogItemResponse(
    val name: String,
    val type: String,
    val link: String,
    val imageUri: String,
    val dateTime: LocalDateTime
)