package feo.health.catalog.data.mapper

import feo.health.catalog.clinic.dto.ClinicDto
import feo.health.catalog.data.mapper.ReviewDtoToReviewDomainMapper.toReviewDomainList
import feo.health.catalog.data.mapper.ReviewDtoToReviewDomainMapper.toReviewDtoList
import feo.health.catalog.domain.model.ClinicDomain
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object ClinicMapper: IMapper<ClinicDto, ClinicDomain> {
    override fun ClinicDto.toSecond(): ClinicDomain =
        ClinicDomain(
            name = name,
            link = link,
            address = address,
            phoneNumber = phoneNumber,
            imageUri = imageUri,
            itemType = itemType,
            reviews = reviews?.toReviewDomainList()
        )

    override fun ClinicDomain.toFirst(): ClinicDto =
        ClinicDto(
            name = name,
            link = link,
            address = address,
            phoneNumber = phoneNumber,
            imageUri = imageUri,
            itemType = itemType,
            reviews = reviews?.toReviewDtoList()
        )
}