package feo.health.catalog.data.mapper

import feo.health.catalog.domain.model.ServiceDomain
import feo.health.catalog.services.dto.ServiceDto

fun ServiceDto.toDomain(): ServiceDomain =
    ServiceDomain(
        name = name,
        link = link,
        itemType = itemType
    )

fun List<ServiceDto>.toDomain(): List<ServiceDomain> =
    this.map { it.toDomain() }