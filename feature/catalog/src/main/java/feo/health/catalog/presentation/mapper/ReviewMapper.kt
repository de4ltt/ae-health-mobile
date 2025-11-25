package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.ReviewDomain
import feo.health.catalog.presentation.model.Review
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object ReviewMapper: IMapper<Review, ReviewDomain> {
    override fun Review.toSecond(): ReviewDomain =
        ReviewDomain(
            text = text,
            date = date,
            rating = rating
        )
    override fun ReviewDomain.toFirst(): Review =
        Review(
            text = text,
            date = date,
            rating = rating
        )
}