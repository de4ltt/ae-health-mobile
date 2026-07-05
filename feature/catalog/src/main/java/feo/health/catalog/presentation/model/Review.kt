package feo.health.catalog.presentation.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import feo.health.ui.R
import feo.health.ui.theme.HColorScheme
import java.time.LocalDate

/**
 * Data class representing a catalog item review.
 *
 * @property text The review feedback text.
 * @property date Optional date when the review was written.
 * @property rating Optional review rating score.
 */
data class Review(
    val text: String,
    val date: LocalDate?,
    val rating: Double?
) {
    companion object {
        /**
         * Enum describing the visual rating status (GOOD, MIXED, BAD) with associated resources and colors.
         *
         * @property definition String resource ID for the rating description.
         * @property color Color representing the rating visually.
         */
        enum class ReviewIndication(
            @param:StringRes val definition: Int,
            val color: Color
        ) {
            GOOD(definition = R.string.good, color = HColorScheme.Additional.GREEN),
            MIXED(definition = R.string.mixed, color = HColorScheme.Additional.ORANGE),
            BAD(definition = R.string.bad, color = HColorScheme.Additional.RED);

            companion object {
                /**
                 * Resolves a rating value to its respective [ReviewIndication].
                 *
                 * @param rating The numerical rating.
                 * @return Corresponding [ReviewIndication].
                 */
                fun defineIndication(rating: Double): ReviewIndication = when {
                    rating < 2.5 -> BAD
                    rating in 2.5..3.75 -> MIXED
                    else -> GOOD
                }

                /**
                 * Resolves a list of reviews to their average [ReviewIndication].
                 *
                 * @param reviews The list of reviews.
                 * @return Corresponding [ReviewIndication] based on the average score.
                 */
                fun defineIndication(reviews: List<Review>): ReviewIndication {
                    val rating = reviews.avg()
                    return defineIndication(rating)
                }
            }
        }

        /**
         * Extension function to calculate the average rating from a list of reviews.
         *
         * @return The average rating score as a [Double].
         */
        fun List<Review>.avg(): Double {
            val notNull = this.mapNotNull { it.rating }
            return notNull.sum() / notNull.count()
        }
    }
}
