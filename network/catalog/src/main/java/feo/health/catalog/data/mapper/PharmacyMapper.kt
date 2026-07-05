package feo.health.catalog.data.mapper

import feo.health.catalog.domain.model.PharmacyDomain
import feo.health.catalog.pharmacy.dto.PharmacyDto
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between serialization [PharmacyDto] and domain [PharmacyDomain] models.
 */
@Mapper
private object PharmacyMapper : IMapper<PharmacyDto, PharmacyDomain> {
    /**
     * Converts a [PharmacyDto] serial model to its corresponding domain [PharmacyDomain] entity.
     *
     * @return Resolved [PharmacyDomain].
     */
    override fun PharmacyDto.toSecond(): PharmacyDomain =
        PharmacyDomain(
            name = name,
            phoneNumber = phoneNumber,
            website = website,
            address = address,
            openingHours = openingHours
        )

    /**
     * Converts a [PharmacyDomain] domain entity to its corresponding serial [PharmacyDto] model.
     *
     * @return Resolved [PharmacyDto].
     */
    override fun PharmacyDomain.toFirst(): PharmacyDto =
        PharmacyDto(
            name = name,
            phoneNumber = phoneNumber,
            website = website,
            address = address,
            openingHours = openingHours
        )
}