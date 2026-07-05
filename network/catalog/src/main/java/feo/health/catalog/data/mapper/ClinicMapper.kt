package feo.health.catalog.data.mapper

import feo.health.catalog.clinic.dto.ClinicDto
import feo.health.catalog.data.mapper.ReviewDtoToReviewDomainMapper.toDomainList
import feo.health.catalog.data.mapper.ReviewDtoToReviewDomainMapper.toDtoList
import feo.health.catalog.domain.model.ClinicDomain
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between serialization [ClinicDto] and domain [ClinicDomain] models.
 */
@Mapper
private object ClinicMapper: IMapper<ClinicDto, ClinicDomain> {
    /**
     * Converts a [ClinicDto] serial model to its corresponding domain [ClinicDomain] entity.
     *
     * @return Resolved [ClinicDomain].
     */
    override fun ClinicDto.toSecond(): ClinicDomain =
        ClinicDomain(
            name = name,
            link = link,
            address = address,
            phoneNumber = phoneNumber,
            imageUri = imageUri,
            itemType = itemType,
            reviews = reviews?.toDomainList()
        )

    /**
     * Converts a [ClinicDomain] domain entity to its corresponding serial [ClinicDto] model.
     *
     * @return Resolved [ClinicDto].
     */
    override fun ClinicDomain.toFirst(): ClinicDto =
        ClinicDto(
            name = name,
            link = link,
            address = address,
            phoneNumber = phoneNumber,
            imageUri = imageUri,
            itemType = itemType,
            reviews = reviews?.toDtoList()
        )
}