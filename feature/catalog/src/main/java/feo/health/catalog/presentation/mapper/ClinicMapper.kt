package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.presentation.mapper.ReviewToReviewDomainMapper.toDomainList
import feo.health.catalog.presentation.mapper.ReviewToReviewDomainMapper.toReviewList
import feo.health.catalog.presentation.model.ICatalog
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Mapper object for translating between [ClinicDomain] and [ICatalog.Clinic].
 *
 * This mapper uses the @Mapper annotation and implements [IMapper].
 */
@Mapper
private object ClinicMapper : IMapper<ClinicDomain, ICatalog.Clinic> {
    /**
     * Converts a [ClinicDomain] instance to [ICatalog.Clinic].
     *
     * @return The converted [ICatalog.Clinic] instance.
     */
    override fun ClinicDomain.toSecond(): ICatalog.Clinic =
        ICatalog.Clinic(
            name = name,
            link = link,
            address = address,
            phoneNumber = phoneNumber,
            imageUri = imageUri,
            itemType = itemType,
            reviews = reviews?.toReviewList()
        )

    /**
     * Converts an [ICatalog.Clinic] instance back to [ClinicDomain].
     *
     * @return The converted [ClinicDomain] instance.
     */
    override fun ICatalog.Clinic.toFirst(): ClinicDomain =
        ClinicDomain(
            name = name,
            link = link,
            address = address,
            phoneNumber = phoneNumber,
            imageUri = imageUri,
            itemType = itemType,
            reviews = reviews?.toDomainList()
        )
}