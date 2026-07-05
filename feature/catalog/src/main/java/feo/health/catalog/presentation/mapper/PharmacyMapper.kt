package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.PharmacyDomain
import feo.health.catalog.presentation.model.ICatalog
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Mapper object for translating between [ICatalog.Pharmacy] and [PharmacyDomain].
 *
 * This mapper uses the @Mapper annotation and implements [IMapper].
 */
@Mapper
private object PharmacyMapper : IMapper<ICatalog.Pharmacy, PharmacyDomain> {
    /**
     * Converts an [ICatalog.Pharmacy] instance to [PharmacyDomain].
     *
     * @return The converted [PharmacyDomain] instance.
     */
    override fun ICatalog.Pharmacy.toSecond(): PharmacyDomain =
        PharmacyDomain(
            name = name,
            phoneNumber = phoneNumber,
            website = website,
            address = address,
            openingHours = openingHours
        )

    /**
     * Converts a [PharmacyDomain] instance back to [ICatalog.Pharmacy].
     *
     * @return The converted [ICatalog.Pharmacy] instance.
     */
    override fun PharmacyDomain.toFirst(): ICatalog.Pharmacy =
        ICatalog.Pharmacy(
            name = name,
            phoneNumber = phoneNumber,
            website = website,
            address = address,
            openingHours = openingHours
        )
}