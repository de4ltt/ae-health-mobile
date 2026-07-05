package feo.health.catalog.data.mapper

import feo.health.catalog.doctor.dto.DoctorSpecialityDto
import feo.health.catalog.domain.model.DoctorSpecialityDomain
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between serialization [DoctorSpecialityDto] and domain [DoctorSpecialityDomain] models.
 */
@Mapper
private object SpecialitiesMapper : IMapper<DoctorSpecialityDto, DoctorSpecialityDomain> {
    /**
     * Converts a [DoctorSpecialityDto] serial model to its corresponding domain [DoctorSpecialityDomain] entity.
     *
     * @return Resolved [DoctorSpecialityDomain].
     */
    override fun DoctorSpecialityDto.toSecond(): DoctorSpecialityDomain =
        DoctorSpecialityDomain(
            name = name,
            link = link
        )

    /**
     * Converts a [DoctorSpecialityDomain] domain entity to its corresponding serial [DoctorSpecialityDto] model.
     *
     * @return Resolved [DoctorSpecialityDto].
     */
    override fun DoctorSpecialityDomain.toFirst(): DoctorSpecialityDto =
        DoctorSpecialityDto(
            name = name,
            link = link
        )
}