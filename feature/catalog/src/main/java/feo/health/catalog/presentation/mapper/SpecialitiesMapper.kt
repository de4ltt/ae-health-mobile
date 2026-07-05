package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.DoctorSpecialityDomain
import feo.health.catalog.presentation.model.DoctorSpeciality
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Mapper object for translating between [DoctorSpeciality] and [DoctorSpecialityDomain].
 *
 * This mapper uses the @Mapper annotation and implements [IMapper].
 */
@Mapper
private object SpecialitiesMapper : IMapper<DoctorSpeciality, DoctorSpecialityDomain> {
    /**
     * Converts a [DoctorSpeciality] instance to [DoctorSpecialityDomain].
     *
     * @return The converted [DoctorSpecialityDomain] instance.
     */
    override fun DoctorSpeciality.toSecond(): DoctorSpecialityDomain =
        DoctorSpecialityDomain(
            name = name,
            link = link
        )

    /**
     * Converts a [DoctorSpecialityDomain] instance back to [DoctorSpeciality].
     *
     * @return The converted [DoctorSpeciality] instance.
     */
    override fun DoctorSpecialityDomain.toFirst(): DoctorSpeciality =
        DoctorSpeciality(
            name = name,
            link = link
        )
}