package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.ReviewDomain
import feo.health.catalog.presentation.model.Review
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Mapper object for translating between [Review] and [ReviewDomain].
 *
 * This mapper uses the @Mapper annotation and implements [IMapper].
 */
@Mapper
private object ReviewMapper: IMapper<Review, ReviewDomain> {
    /**
     * Converts a [Review] instance to [ReviewDomain].
     *
     * @return The converted [ReviewDomain] instance.
     */
    override fun Review.toSecond(): ReviewDomain =
        ReviewDomain(
            text = text,
            date = date,
            rating = rating
        )

    /**
     * Converts a [ReviewDomain] instance back to [Review].
     *
     * @return The converted [Review] instance.
     */
    override fun ReviewDomain.toFirst(): Review =
        Review(
            text = text,
            date = date,
            rating = rating
        )
}