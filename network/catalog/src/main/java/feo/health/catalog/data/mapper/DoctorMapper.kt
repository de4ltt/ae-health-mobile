package feo.health.catalog.data.mapper

import feo.health.catalog.data.mapper.DoctorSpecialityDtoToDoctorSpecialityDomainMapper.toDomainList as toSpecialityDomainList
import feo.health.catalog.data.mapper.DoctorSpecialityDtoToDoctorSpecialityDomainMapper.toDtoList as toSpecialityDtoList
import feo.health.catalog.data.mapper.ReviewDtoToReviewDomainMapper.toDomainList as toReviewDomainList
import feo.health.catalog.data.mapper.ReviewDtoToReviewDomainMapper.toDtoList as toReviewDtoList
import feo.health.catalog.doctor.dto.DoctorDto
import feo.health.catalog.domain.model.DoctorDomain
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object DoctorMapper: IMapper<DoctorDto, DoctorDomain> {
    override fun DoctorDto.toSecond(): DoctorDomain =
        DoctorDomain(
            name = name,
            link = link,
            specialities = specialities?.toSpecialityDomainList(),
            experience = experience,
            imageUri = imageUri,
            rating = rating,
            itemType = itemType,
            reviews = reviews?.toReviewDomainList()
        )

    override fun DoctorDomain.toFirst(): DoctorDto =
        DoctorDto(
            name = name,
            link = link,
            specialities = specialities?.toSpecialityDtoList(),
            experience = experience,
            imageUri = imageUri,
            rating = rating,
            itemType = itemType,
            reviews = reviews?.toReviewDtoList()
        )

}