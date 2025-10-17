package feo.health.catalog.search.dto

import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class ReviewDto(
    val text: String,
    val date: LocalDate?,
    val rating: Double?
)
