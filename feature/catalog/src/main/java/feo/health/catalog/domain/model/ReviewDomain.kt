package feo.health.catalog.domain.model

import java.time.LocalDate

data class ReviewDomain(
    val text: String,
    val date: LocalDate?,
    val rating: Double?
)
