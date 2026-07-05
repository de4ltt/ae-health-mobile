package feo.health.user.components.domain.model

import java.time.LocalDateTime

/**
 * Domain model representing a catalog item.
 *
 * @property name The name/title of the catalog item.
 * @property type The classification or category of the catalog item.
 * @property link The optional web URL link associated with the item.
 * @property imageUri The optional image URI path or URL representing the catalog item.
 * @property dateTime The local timestamp when this catalog item was recorded or interacted with.
 */
data class CatalogItemDomain(
    val name: String,
    val type: String,
    val link: String?,
    val imageUri: String?,
    val dateTime: LocalDateTime
)