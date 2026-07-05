package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.DrugDomain
import feo.health.catalog.presentation.mapper.DrugFormDomainToDrugFormMapper.toDomainList as toDrugFormDomainList
import feo.health.catalog.presentation.mapper.DrugFormDomainToDrugFormMapper.toDrugFormList
import feo.health.catalog.presentation.mapper.InstructionSectionDomainToInstructionSectionMapper.toDomainList as toInstructionSectionDomainList
import feo.health.catalog.presentation.mapper.InstructionSectionDomainToInstructionSectionMapper.toInstructionSectionList
import feo.health.catalog.presentation.model.Drug
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Mapper object for translating between [Drug] and [DrugDomain].
 *
 * This mapper uses the @Mapper annotation and implements [IMapper].
 */
@Mapper
private object DrugMapper : IMapper<Drug, DrugDomain> {
    /**
     * Converts a [Drug] instance to [DrugDomain].
     *
     * @return The converted [DrugDomain] instance.
     */
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

    /**
     * Converts a [DrugDomain] instance back to [Drug].
     *
     * @return The converted [Drug] instance.
     */
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

    /**
     * Nested mapper for converting between [DrugDomain.DrugFormDomain] and [Drug.DrugForm].
     */
    @Mapper
    private object DrugFormMapper : IMapper<DrugDomain.DrugFormDomain, Drug.DrugForm> {
        /**
         * Converts [DrugDomain.DrugFormDomain] to [Drug.DrugForm].
         *
         * @return The converted [Drug.DrugForm] instance.
         */
        override fun DrugDomain.DrugFormDomain.toSecond(): Drug.DrugForm =
            Drug.DrugForm(
                formName = formName,
                dosage = dosage,
                packaging = packaging,
                storage = storage,
                sale = sale,
                shelfLife = shelfLife
            )

        /**
         * Converts [Drug.DrugForm] to [DrugDomain.DrugFormDomain].
         *
         * @return The converted [DrugDomain.DrugFormDomain] instance.
         */
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

    /**
     * Nested mapper for converting between [DrugDomain.InstructionSectionDomain] and [Drug.InstructionSection].
     */
    @Mapper
    private object InstructionsSectionMapper :
        IMapper<DrugDomain.InstructionSectionDomain, Drug.InstructionSection> {
        /**
         * Converts [DrugDomain.InstructionSectionDomain] to [Drug.InstructionSection].
         *
         * @return The converted [Drug.InstructionSection] instance.
         */
        override fun DrugDomain.InstructionSectionDomain.toSecond(): Drug.InstructionSection =
            Drug.InstructionSection(
                title = title,
                text = text
            )

        /**
         * Converts [Drug.InstructionSection] to [DrugDomain.InstructionSectionDomain].
         *
         * @return The converted [DrugDomain.InstructionSectionDomain] instance.
         */
        override fun Drug.InstructionSection.toFirst(): DrugDomain.InstructionSectionDomain =
            DrugDomain.InstructionSectionDomain(
                title = title,
                text = text
            )
    }
}