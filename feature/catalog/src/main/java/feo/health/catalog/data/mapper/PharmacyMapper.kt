package feo.health.catalog.data.mapper

import feo.health.catalog.domain.model.PharmacyDomain
import feo.health.catalog.pharmacy.dto.PharmacyDto
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object PharmacyMapper : IMapper<PharmacyDto, PharmacyDomain> {
    override fun PharmacyDto.toSecond(): PharmacyDomain =
        PharmacyDomain(
            name = name,
            phoneNumber = phoneNumber,
            website = website,
            address = address,
            openingHours = openingHours
        )

    override fun PharmacyDomain.toFirst(): PharmacyDto =
        PharmacyDto(
            name = name,
            phoneNumber = phoneNumber,
            website = website,
            address = address,
            openingHours = openingHours
        )
}