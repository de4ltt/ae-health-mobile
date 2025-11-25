package feo.health.catalog.presentation.mapper

import feo.health.catalog.doctor.dto.DoctorSpecialityDto
import feo.health.catalog.domain.model.DoctorSpecialityDomain
import feo.health.catalog.presentation.model.DoctorSpeciality
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object SpecialitiesMapper : IMapper<DoctorSpeciality, DoctorSpecialityDomain> {
    override fun DoctorSpeciality.toSecond(): DoctorSpecialityDomain =
        DoctorSpecialityDomain(
            name = name,
            link = link
        )

    override fun DoctorSpecialityDomain.toFirst(): DoctorSpeciality =
        DoctorSpeciality(
            name = name,
            link = link
        )
}