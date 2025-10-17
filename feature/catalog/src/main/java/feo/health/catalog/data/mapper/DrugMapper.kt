package feo.health.catalog.data.mapper

import feo.health.catalog.domain.model.DrugDomain
import feo.health.catalog.drug.dto.DrugDto

fun DrugDto.toDomain(): DrugDomain = DrugDomain(
    name = name,
    latinName = latinName,
    link = link,
    imageUri = imageUri,
    effectiveness = effectiveness,
    rating = rating,
    priceQuality = priceQuality,
    sideEffects = sideEffects,
    reviewsCount = reviewsCount,
    forms = forms.toDrugFormDomainList(),
    instructionSections = instructionSections.toInstructionSectionDomainList()
)

fun List<DrugDto>.toDrugDomainList(): List<DrugDomain> =
    map { it.toDomain() }

fun DrugDto.DrugFormDto.toDomain(): DrugDomain.DrugFormDomain =
    DrugDomain.DrugFormDomain(
        formName = formName,
        dosage = dosage,
        packaging = packaging,
        storage = storage,
        sale = sale,
        shelfLife = shelfLife
    )

fun List<DrugDto.DrugFormDto>.toDrugFormDomainList(): List<DrugDomain.DrugFormDomain> =
    map { it.toDomain() }

fun DrugDto.InstructionSectionDto.toDomain(): DrugDomain.InstructionSectionDomain =
    DrugDomain.InstructionSectionDomain(
        title = title,
        text = text
    )

fun List<DrugDto.InstructionSectionDto>.toInstructionSectionDomainList(): List<DrugDomain.InstructionSectionDomain> =
    map { it.toDomain() }
