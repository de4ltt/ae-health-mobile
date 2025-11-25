package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.DrugDomain
import feo.health.catalog.presentation.mapper.DrugFormDomainToDrugFormMapper.toDrugFormDomainList
import feo.health.catalog.presentation.mapper.DrugFormDomainToDrugFormMapper.toDrugFormList
import feo.health.catalog.presentation.mapper.InstructionSectionDomainToInstructionSectionMapper.toInstructionSectionDomainList
import feo.health.catalog.presentation.mapper.InstructionSectionDomainToInstructionSectionMapper.toInstructionSectionList
import feo.health.catalog.presentation.model.Drug
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object DrugMapper : IMapper<Drug, DrugDomain> {
    override fun Drug.toSecond(): DrugDomain = DrugDomain(
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

    override fun DrugDomain.toFirst(): Drug =
        Drug(
            name = name,
            latinName = latinName,
            link = link,
            imageUri = imageUri,
            effectiveness = effectiveness,
            rating = rating,
            priceQuality = priceQuality,
            sideEffects = sideEffects,
            reviewsCount = reviewsCount,
            forms = forms.toDrugFormList(),
            instructionSections = instructionSections.toInstructionSectionList()
        )

    @Mapper
    private object DrugFormMapper : IMapper<DrugDomain.DrugFormDomain, Drug.DrugForm> {
        override fun DrugDomain.DrugFormDomain.toSecond(): Drug.DrugForm =
            Drug.DrugForm(
                formName = formName,
                dosage = dosage,
                packaging = packaging,
                storage = storage,
                sale = sale,
                shelfLife = shelfLife
            )

        override fun Drug.DrugForm.toFirst(): DrugDomain.DrugFormDomain =
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
        IMapper<DrugDomain.InstructionSectionDomain, Drug.InstructionSection> {
        override fun DrugDomain.InstructionSectionDomain.toSecond(): Drug.InstructionSection =
            Drug.InstructionSection(
                title = title,
                text = text
            )

        override fun Drug.InstructionSection.toFirst(): DrugDomain.InstructionSectionDomain =
            DrugDomain.InstructionSectionDomain(
                title = title,
                text = text
            )
    }
}