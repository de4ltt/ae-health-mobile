package feo.health.catalog.domain.model

import java.time.LocalDate

/**
 * Domain representation of a User Review.
 *
 * This data class holds user feedback, including the review content/text, review date,
 * and a numeric rating.
 *
 * @property text The text content of the review.
 * @property date The publication date of the review, if available.
 * @property rating The rating given by the user, if available.
 */
data class ReviewDomain(
    val text: String,
    val date: LocalDate?,
    val rating: Double?
)
