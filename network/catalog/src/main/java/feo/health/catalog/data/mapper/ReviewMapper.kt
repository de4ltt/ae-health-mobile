package feo.health.catalog.data.mapper

import feo.health.catalog.domain.model.ReviewDomain
import feo.health.catalog.search.dto.ReviewDto
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between serialization [ReviewDto] and domain [ReviewDomain] models.
 */
@Mapper
private object ReviewMapper : IMapper<ReviewDto, ReviewDomain> {
    /**
     * Converts a [ReviewDto] serial model to its corresponding domain [ReviewDomain] entity.
     *
     * @return Resolved [ReviewDomain].
     */
    override fun ReviewDto.toSecond(): ReviewDomain =
        ReviewDomain(
            text = text ?: "",
            date = date,
            rating = rating
        )

    /**
     * Converts a [ReviewDomain] domain entity to its corresponding serial [ReviewDto] model.
     *
     * @return Resolved [ReviewDto].
     */
    override fun ReviewDomain.toFirst(): ReviewDto =
        ReviewDto(
            text = text,
            date = date,
            rating = rating
        )
}