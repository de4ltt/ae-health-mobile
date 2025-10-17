package feo.health.catalog.presentation.model

import java.time.LocalDate

data class Review(
    val text: String,
    val date: LocalDate?,
    val rating: Double?
)
