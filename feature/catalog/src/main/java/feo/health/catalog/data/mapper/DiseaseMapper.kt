package feo.health.catalog.data.mapper

import feo.health.catalog.disease.dto.DiseaseDto
import feo.health.catalog.domain.model.DiseaseDomain

fun DiseaseDto.toDomain(): DiseaseDomain =
    DiseaseDomain(
        name = name,
        link = link
    )

fun List<DiseaseDto>.toDomain(): List<DiseaseDomain> =
    this.map { it.toDomain() }