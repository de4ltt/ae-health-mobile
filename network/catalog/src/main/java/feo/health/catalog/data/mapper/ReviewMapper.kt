package feo.health.catalog.data.mapper

import feo.health.catalog.domain.model.ReviewDomain
import feo.health.catalog.search.dto.ReviewDto
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object ReviewMapper : IMapper<ReviewDto, ReviewDomain> {
    override fun ReviewDto.toSecond(): ReviewDomain =
        ReviewDomain(
            text = text ?: "",
            date = date,
            rating = rating
        )
    override fun ReviewDomain.toFirst(): ReviewDto =
        ReviewDto(
            text = text,
            date = date,
            rating = rating
        )
}