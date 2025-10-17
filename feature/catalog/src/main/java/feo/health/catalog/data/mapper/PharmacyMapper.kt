package feo.health.catalog.data.mapper

import feo.health.catalog.domain.model.PharmacyDomain
import feo.health.catalog.pharmacy.dto.PharmacyDto

fun PharmacyDto.toDomain(): PharmacyDomain =
    PharmacyDomain(
        name = name,
        phoneNumber = phoneNumber,
        website = website,
        address = address,
        openingHours = openingHours
    )

fun List<PharmacyDto>.toDomain(): List<PharmacyDomain> =
    this.map { it.toDomain() }

fun PharmacyDomain.toDto(): PharmacyDto =
    PharmacyDto(
        name = name,
        phoneNumber = phoneNumber,
        website = website,
        address = address,
        openingHours = openingHours
    )