package feo.health.user.components.data.mapper

import feo.health.mapper.IMapper
import feo.health.mapper.Mapper
import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.dto.response.CatalogItemResponse

@Mapper
private object CatalogItemMapper : IMapper<CatalogItemDomain, CatalogItemResponse> {
    override fun CatalogItemDomain.toSecond(): CatalogItemResponse = CatalogItemResponse(
        name = name,
        type = type,
        link = link,
        imageUrl = imageUri,
        dateTime = dateTime
    )

    override fun CatalogItemResponse.toFirst(): CatalogItemDomain = CatalogItemDomain(
        name = name,
        type = type,
        link = link,
        imageUri = imageUrl?.replace(Regex("\\s+"), ""),
        dateTime = dateTime
    )
}