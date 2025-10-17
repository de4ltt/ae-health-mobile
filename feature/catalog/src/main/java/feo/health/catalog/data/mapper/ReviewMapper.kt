package feo.health.catalog.data.mapper

import feo.health.catalog.domain.model.ReviewDomain
import feo.health.catalog.search.dto.ReviewDto

fun ReviewDto.toDomain(): ReviewDomain =
    ReviewDomain(
        text = text,
        date = date,
        rating = rating
    )

fun List<ReviewDto>.toDomain(): List<ReviewDomain> =
    this.map { it.toDomain() }