package feo.health.catalog.domain.model

/**
 * Domain representation of a Drug.
 *
 * This data class contains comprehensive details about a pharmaceutical drug,
 * including its names, ratings, forms, and instructions.
 *
 * @property name The localized name of the drug.
 * @property latinName The Latin name of the drug, if available.
 * @property link The detail link for the drug.
 * @property imageUri The image URI of the drug packaging, if available.
 * @property effectiveness The effectiveness rating of the drug, if available.
 * @property rating The overall user rating of the drug, if available.
 * @property priceQuality The price-to-quality rating of the drug, if available.
 * @property sideEffects The side-effects rating of the drug, if available.
 * @property reviewsCount The total number of reviews for the drug, if available.
 * @property forms The list of available forms and dosages of the drug.
 * @property instructionSections The detailed medical instructions for the drug.
 */
data class DrugDomain(
    val name: String,
    val latinName: String?,
    val link: String,
    val imageUri: String?,
    val effectiveness: Double?,
    val rating: Double?,
    val priceQuality: Double?,
    val sideEffects: Double?,
    val reviewsCount: Int?,
    val forms: List<DrugFormDomain>,
    val instructionSections: List<InstructionSectionDomain>
) {
    /**
     * Domain representation of a specific form of a drug.
     *
     * @property formName The physical form of the drug (e.g. tablet, capsule, syrup), if available.
     * @property dosage The dosage information of the drug, if available.
     * @property packaging The packaging description, if available.
     * @property storage The storage guidelines, if available.
     * @property sale The sale terms (e.g. over-the-counter or prescription), if available.
     * @property shelfLife The shelf life duration, if available.
     */
    data class DrugFormDomain(
        val formName: String?,
        val dosage: String?,
        val packaging: String?,
        val storage: String?,
        val sale: String?,
        val shelfLife: String?
    )

    /**
     * Domain representation of a section within the drug instruction manual.
     *
     * @property title The header/title of the instruction section, if available.
     * @property text The detailed description/text of the instruction section, if available.
     */
    data class InstructionSectionDomain(
        val title: String?,
        val text: String?
    )
}
