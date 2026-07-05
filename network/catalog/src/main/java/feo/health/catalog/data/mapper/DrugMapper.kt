package feo.health.catalog.data.mapper

import feo.health.catalog.data.mapper.DrugFormDomainToDrugFormDtoMapper.toDomainList as toFormDomainList
import feo.health.catalog.data.mapper.DrugFormDomainToDrugFormDtoMapper.toDtoList as toFormDtoList
import feo.health.catalog.data.mapper.InstructionSectionDomainToInstructionSectionDtoMapper.toDomainList as toInstructionDomainList
import feo.health.catalog.data.mapper.InstructionSectionDomainToInstructionSectionDtoMapper.toDtoList as toInstructionDtoList
import feo.health.catalog.domain.model.DrugDomain
import feo.health.catalog.drug.dto.DrugDto
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object DrugMapper : IMapper<DrugDto, DrugDomain> {
    override fun DrugDto.toSecond(): DrugDomain = DrugDomain(
        name = name,
        latinName = latinName,
        link = link,
        imageUri = imageUri,
        effectiveness = effectiveness,
        rating = rating,
        priceQuality = priceQuality,
        sideEffects = sideEffects,
        reviewsCount = reviewsCount,
        forms = forms.toFormDomainList(),
        instructionSections = instructionSections.toInstructionDomainList()
    )

    override fun DrugDomain.toFirst(): DrugDto =
        DrugDto(
            name = name,
            latinName = latinName,
            link = link,
            imageUri = imageUri,
            effectiveness = effectiveness,
            rating = rating,
            priceQuality = priceQuality,
            sideEffects = sideEffects,
            reviewsCount = reviewsCount,
            forms = forms.toFormDtoList(),
            instructionSections = instructionSections.toInstructionDtoList()
        )

    @Mapper
    private object DrugFormMapper : IMapper<DrugDomain.DrugFormDomain, DrugDto.DrugFormDto> {
        override fun DrugDomain.DrugFormDomain.toSecond(): DrugDto.DrugFormDto =
            DrugDto.DrugFormDto(
                formName = formName,
                dosage = dosage,
                packaging = packaging,
                storage = storage,
                sale = sale,
                shelfLife = shelfLife
            )

        override fun DrugDto.DrugFormDto.toFirst(): DrugDomain.DrugFormDomain =
            DrugDomain.DrugFormDomain(
                formName = formName,
                dosage = dosage,
                packaging = packaging,
                storage = storage,
                sale = sale,
                shelfLife = shelfLife
            )
    }

    @Mapper
    private object InstructionsSectionMapper :
        IMapper<DrugDomain.InstructionSectionDomain, DrugDto.InstructionSectionDto> {
        override fun DrugDomain.InstructionSectionDomain.toSecond(): DrugDto.InstructionSectionDto =
            DrugDto.InstructionSectionDto(
                title = title,
                text = text
            )

        override fun DrugDto.InstructionSectionDto.toFirst(): DrugDomain.InstructionSectionDomain =
            DrugDomain.InstructionSectionDomain(
                title = title,
                text = text
            )
    }
}