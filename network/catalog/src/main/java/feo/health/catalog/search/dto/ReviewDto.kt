package feo.health.catalog.search.dto

import feo.health.network.util.LocalDateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class ReviewDto(
    val text: String? = null,
    @Serializable(with = LocalDateSerializer::class)
    val date: LocalDate? = null,
    val rating: Double? = null
)
