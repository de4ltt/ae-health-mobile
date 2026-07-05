package feo.health.user.components.presentation.mapper

import feo.health.mapper.IMapper
import feo.health.mapper.Mapper
import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.presentation.model.UCatalogItem

/**
 * Mapper that converts [CatalogItemDomain] (domain model) to [UCatalogItem] (presentation model) and vice-versa.
 * Uses a mapping based on the item type (service, clinic, doctor, pharmacy).
 */
@Mapper
private object CatalogItemMapper : IMapper<CatalogItemDomain, UCatalogItem> {
    /**
     * Converts a [CatalogItemDomain] domain model to its corresponding [UCatalogItem] presentation model representation.
     *
     * @return The converted [UCatalogItem].
     * @throws ClassNotFoundException if the domain item type is unknown.
     */
    override fun CatalogItemDomain.toSecond(): UCatalogItem = when(this.type) {
        "service" -> UCatalogItem.UServiceItem(title = name, imageUri = imageUri, link = link, dateTime = dateTime)
        "clinic" -> UCatalogItem.UClinicItem(title = name, imageUri = imageUri, link = link, dateTime = dateTime)
        "doctor" -> UCatalogItem.UDoctorItem(title = name, imageUri = imageUri, link = link, dateTime = dateTime)
        "pharmacy" -> UCatalogItem.UPharmacyItem(title = name, imageUri = imageUri, link = link, dateTime = dateTime)
        else -> throw ClassNotFoundException("")
    } as UCatalogItem

    /**
     * Converts a [UCatalogItem] presentation model back to its [CatalogItemDomain] domain model representation.
     *
     * @return The converted [CatalogItemDomain].
     */
    override fun UCatalogItem.toFirst(): CatalogItemDomain = CatalogItemDomain(
        name = title,
        type = type.name.lowercase(),
        link = link,
        imageUri = imageUri,
        dateTime = dateTime
    )
}