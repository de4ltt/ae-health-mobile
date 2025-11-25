package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.presentation.mapper.ReviewToReviewDomainMapper.toReviewDomainList
import feo.health.catalog.presentation.mapper.ReviewToReviewDomainMapper.toReviewList
import feo.health.catalog.presentation.model.ICatalog
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object ClinicMapper : IMapper<ClinicDomain, ICatalog.Clinic> {
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

    override fun ICatalog.Clinic.toFirst(): ClinicDomain =
        ClinicDomain(
            name = name,
            link = link,
            address = address,
            phoneNumber = phoneNumber,
            imageUri = imageUri,
            itemType = itemType,
            reviews = reviews?.toReviewDomainList()
        )
}