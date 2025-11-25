package feo.health.user.components.presentation.mapper

import feo.health.mapper.IMapper
import feo.health.mapper.Mapper
import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.presentation.model.UCatalogItem

@Mapper
private object CatalogItemMapper : IMapper<CatalogItemDomain, UCatalogItem> {
    override fun CatalogItemDomain.toSecond(): UCatalogItem = when(this.type) {
        "service" -> UCatalogItem.UServiceItem(title = name, imageUri = imageUri, link = link, dateTime = dateTime)
        "clinic" -> UCatalogItem.UClinicItem(title = name, imageUri = imageUri, link = link, dateTime = dateTime)
        "doctor" -> UCatalogItem.UDoctorItem(title = name, imageUri = imageUri, link = link, dateTime = dateTime)
        "pharmacy" -> UCatalogItem.UPharmacyItem(title = name, imageUri = imageUri, link = link, dateTime = dateTime)
        else -> throw ClassNotFoundException("")
    } as UCatalogItem

    override fun UCatalogItem.toFirst(): CatalogItemDomain = CatalogItemDomain(
        name = title,
        type = type.name.lowercase(),
        link = link,
        imageUri = imageUri,
        dateTime = dateTime
    )
}