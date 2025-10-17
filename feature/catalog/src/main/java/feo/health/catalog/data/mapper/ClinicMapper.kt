package feo.health.catalog.data.mapper

import feo.health.catalog.clinic.dto.ClinicDto
import feo.health.catalog.domain.model.ClinicDomain

fun ClinicDto.toDomain(): ClinicDomain =
    ClinicDomain(
        name = name,
        link = link,
        address = address,
        phoneNumber = phoneNumber,
        imageUri = imageUri,
        itemType = itemType,
        reviews = reviews?.toDomain()
    )

fun List<ClinicDto>.toDomain(): List<ClinicDomain> =
    this.map { it.toDomain() }