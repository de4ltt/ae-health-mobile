package feo.health.user.dto.response

import feo.health.network.util.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * Data transfer response object containing catalog item action details (e.g. from history list).
 *
 * @property name Catalog entry name (e.g. name of doctor or clinic).
 * @property type Entry type identifier (e.g. "doctor" or "clinic").
 * @property link Navigational route key link.
 * @property imageUrl Location path of matched image asset.
 * @property dateTime Time stamp when action occurred. Serialized using [LocalDateTimeSerializer].
 */
@Serializable
data class CatalogItemResponse(
    val name: String,
    val type: String,
    val link: String?,
    val imageUrl: String?,
    @Serializable(LocalDateTimeSerializer::class)
    val dateTime: LocalDateTime
)