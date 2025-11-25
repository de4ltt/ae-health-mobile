package feo.health.user.dto.response

import feo.health.network.util.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class CatalogItemResponse(
    val name: String,
    val type: String,
    val link: String?,
    val imageUrl: String?,
    @Serializable(LocalDateTimeSerializer::class)
    val dateTime: LocalDateTime
)