package feo.health.catalog.presentation.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import feo.health.ui.resource.HStrings
import feo.health.ui.theme.HColorScheme
import java.time.LocalDate

data class Review(
    val text: String,
    val date: LocalDate?,
    val rating: Double?
) {
    companion object {
        enum class ReviewIndication(
            @param:StringRes val definition: Int,
            val color: Color
        ) {
            GOOD(definition = HStrings.goodRes, color = HColorScheme.Additional.GREEN),
            MIXED(definition = HStrings.mixedRes, color = HColorScheme.Additional.ORANGE),
            BAD(definition = HStrings.badRes, color = HColorScheme.Additional.RED);

            companion object {
                fun defineIndication(rating: Double): ReviewIndication = when {
                    rating < 2.5 -> BAD
                    rating in 2.5..3.75 -> MIXED
                    else -> GOOD
                }

                fun defineIndication(reviews: List<Review>): ReviewIndication {
                    val rating = reviews.avg()
                    return defineIndication(rating)
                }
            }
        }

        fun List<Review>.avg(): Double {
            val notNull = this.mapNotNull { it.rating }
            return notNull.sum() / notNull.count()
        }
    }
}
