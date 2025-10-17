package feo.health.catalog.data.mapper

import feo.health.catalog.doctor.dto.DoctorSpeciality
import feo.health.catalog.domain.model.DoctorSpecialityDomain

fun DoctorSpeciality.toDomain(): DoctorSpecialityDomain =
    DoctorSpecialityDomain(
        name = name,
        link = link
    )

fun List<DoctorSpeciality>.toDomain(): List<DoctorSpecialityDomain> =
    this.map { it.toDomain() }