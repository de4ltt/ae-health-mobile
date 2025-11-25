package feo.health.catalog.data.mapper

import feo.health.catalog.data.mapper.DoctorSpecialityDtoToDoctorSpecialityDomainMapper.toDoctorSpecialityDomainList
import feo.health.catalog.data.mapper.DoctorSpecialityDtoToDoctorSpecialityDomainMapper.toDoctorSpecialityDtoList
import feo.health.catalog.data.mapper.ReviewDtoToReviewDomainMapper.toReviewDomainList
import feo.health.catalog.data.mapper.ReviewDtoToReviewDomainMapper.toReviewDtoList
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
            specialities = specialities?.toDoctorSpecialityDomainList(),
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
            specialities = specialities?.toDoctorSpecialityDtoList(),
            experience = experience,
            imageUri = imageUri,
            rating = rating,
            itemType = itemType,
            reviews = reviews?.toReviewDtoList()
        )

}