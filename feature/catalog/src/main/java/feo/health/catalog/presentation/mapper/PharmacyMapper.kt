package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.PharmacyDomain
import feo.health.catalog.pharmacy.dto.PharmacyDto
import feo.health.catalog.presentation.model.ICatalog
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object PharmacyMapper : IMapper<ICatalog.Pharmacy, PharmacyDomain> {
    override fun ICatalog.Pharmacy.toSecond(): PharmacyDomain =
        PharmacyDomain(
            name = name,
            phoneNumber = phoneNumber,
            website = website,
            address = address,
            openingHours = openingHours
        )

    override fun PharmacyDomain.toFirst(): ICatalog.Pharmacy =
        ICatalog.Pharmacy(
            name = name,
            phoneNumber = phoneNumber,
            website = website,
            address = address,
            openingHours = openingHours
        )
}