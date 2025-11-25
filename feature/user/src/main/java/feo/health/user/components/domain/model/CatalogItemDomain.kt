package feo.health.user.components.domain.model

import java.time.LocalDateTime

data class CatalogItemDomain(
    val name: String,
    val type: String,
    val link: String?,
    val imageUri: String?,
    val dateTime: LocalDateTime
)