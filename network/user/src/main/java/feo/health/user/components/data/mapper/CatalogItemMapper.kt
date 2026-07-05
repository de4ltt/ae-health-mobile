package feo.health.user.components.data.mapper

import feo.health.mapper.IMapper
import feo.health.mapper.Mapper
import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.dto.response.CatalogItemResponse

/**
 * Data mapping resolver between serialization [CatalogItemResponse] and domain [CatalogItemDomain] models.
 */
@Mapper
private object CatalogItemMapper : IMapper<CatalogItemDomain, CatalogItemResponse> {
    /**
     * Converts a [CatalogItemDomain] domain entity to its corresponding serial [CatalogItemResponse] model.
     *
     * @return Resolved [CatalogItemResponse].
     */
    override fun CatalogItemDomain.toSecond(): CatalogItemResponse = CatalogItemResponse(
        name = name,
        type = type,
        link = link,
        imageUrl = imageUri,
        dateTime = dateTime
    )

    /**
     * Converts a [CatalogItemResponse] serial model to its corresponding domain [CatalogItemDomain] entity.
     * Sanitizes whitespaces from image URLs.
     *
     * @return Resolved [CatalogItemDomain].
     */
    override fun CatalogItemResponse.toFirst(): CatalogItemDomain = CatalogItemDomain(
        name = name,
        type = type,
        link = link,
        imageUri = imageUrl?.replace(Regex("\\s+"), ""),
        dateTime = dateTime
    )
}