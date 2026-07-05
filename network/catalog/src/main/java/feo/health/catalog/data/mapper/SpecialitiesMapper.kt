package feo.health.catalog.data.mapper

import feo.health.catalog.doctor.dto.DoctorSpecialityDto
import feo.health.catalog.domain.model.DoctorSpecialityDomain
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object SpecialitiesMapper : IMapper<DoctorSpecialityDto, DoctorSpecialityDomain> {
    override fun DoctorSpecialityDto.toSecond(): DoctorSpecialityDomain =
        DoctorSpecialityDomain(
            name = name,
            link = link
        )

    override fun DoctorSpecialityDomain.toFirst(): DoctorSpecialityDto =
        DoctorSpecialityDto(
            name = name,
            link = link
        )
}