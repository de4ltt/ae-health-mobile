package feo.health.catalog.drug.dto

import kotlinx.serialization.Serializable

/**
 * Data transfer object mapping drug medication details.
 *
 * @property name Drug common name.
 * @property latinName Drug international scientific name.
 * @property link Navigational identifier link.
 * @property imageUri Endpoint location of the drug package banner.
 * @property effectiveness Average score of drug diagnostic effectiveness.
 * @property rating Overall feedback rating score.
 * @property priceQuality Price-to-quality user feedback score.
 * @property sideEffects Side effects probability metric.
 * @property reviewsCount Total review records count.
 * @property forms List of drug packaging/dosage form details.
 * @property instructionSections Medical guide sections list details.
 */
@Serializable
data class DrugDto(
    val name: String,
    val latinName: String?,
    val link: String,
    val imageUri: String?,
    val effectiveness: Double?,
    val rating: Double?,
    val priceQuality: Double?,
    val sideEffects: Double?,
    val reviewsCount: Int?,
    val forms: List<DrugFormDto>,
    val instructionSections: List<InstructionSectionDto>
) {
    /**
     * Details about a specific pharmaceutical dosage form and packaging configuration.
     *
     * @property formName Dosage form description (e.g. tablet, capsule).
     * @property dosage Active substance dosage concentration.
     * @property packaging Package size and count details.
     * @property storage Safe environment storage rules.
     * @property sale Regulatory sale rules.
     * @property shelfLife Expiry lifecycle length.
     */
    @Serializable
    data class DrugFormDto(
        val formName: String?,
        val dosage: String?,
        val packaging: String?,
        val storage: String?,
        val sale: String?,
        val shelfLife: String?
    )

    /**
     * Medical guide/instruction text section details.
     *
     * @property title Guide section title header.
     * @property text Detailed guide instruction text.
     */
    @Serializable
    data class InstructionSectionDto(
        val title: String?,
        val text: String?
    )
}
