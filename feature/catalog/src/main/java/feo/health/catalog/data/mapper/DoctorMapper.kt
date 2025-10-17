package feo.health.catalog.data.mapper

import feo.health.catalog.doctor.dto.DoctorDto
import feo.health.catalog.domain.model.DoctorDomain

fun DoctorDto.toDomain(): DoctorDomain =
    DoctorDomain(
        name = name,
        link = link,
        specialities = specialities?.toDomain(),
        experience = experience,
        imageUri = imageUri,
        rating = rating,
        itemType = itemType,
        reviews = reviews?.toDomain()
    )

fun List<DoctorDto>.toDomain(): List<DoctorDomain> =
    this.map { it.toDomain() }