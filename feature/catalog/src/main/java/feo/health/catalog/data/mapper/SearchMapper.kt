package feo.health.catalog.data.mapper

import feo.health.catalog.domain.model.SearchDomain
import feo.health.catalog.search.dto.SearchDto

fun SearchDto.toDomain(): SearchDomain =
    SearchDomain(
        doctors = doctors.toDomain(),
        clinics = clinics.toDomain(),
        services = services.toDomain()
    )