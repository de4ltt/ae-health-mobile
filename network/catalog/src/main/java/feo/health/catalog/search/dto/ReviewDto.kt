package feo.health.catalog.search.dto

import feo.health.network.util.LocalDateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

/**
 * Data transfer object mapping user feedback review metrics returned by endpoints.
 *
 * @property text Written feedback description, if available.
 * @property date Date when the review was written. Serialized using [LocalDateSerializer].
 * @property rating Numeric score value assigned, if available.
 */
@Serializable
data class ReviewDto(
    val text: String? = null,
    @Serializable(with = LocalDateSerializer::class)
    val date: LocalDate? = null,
    val rating: Double? = null
)
