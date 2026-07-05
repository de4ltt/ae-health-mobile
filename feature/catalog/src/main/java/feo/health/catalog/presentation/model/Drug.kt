package feo.health.catalog.presentation.model

/**
 * Data class representing a drug catalog item with detailed properties and sections.
 *
 * @property name The common name of the drug.
 * @property latinName The Latin/scientific name of the drug.
 * @property link The details link/resource path.
 * @property imageUri Image resource URI.
 * @property effectiveness Calculated rating for drug effectiveness.
 * @property rating Overall user rating.
 * @property priceQuality Price to quality ratio rating.
 * @property sideEffects Side effects severity rating.
 * @property reviewsCount The number of user reviews.
 * @property forms The list of available drug forms (dosage forms, packagings).
 * @property instructionSections The list of instruction sections for use.
 */
data class Drug(
    val name: String,
    val latinName: String?,
    val link: String,
    val imageUri: String?,
    val effectiveness: Double?,
    val rating: Double?,
    val priceQuality: Double?,
    val sideEffects: Double?,
    val reviewsCount: Int?,
    val forms: List<DrugForm>,
    val instructionSections: List<InstructionSection>
) {
    /**
     * Data class detailing a specific form, packaging, and dosage of the drug.
     *
     * @property formName The name of the drug form (e.g. tablet, capsule).
     * @property dosage The dosage strength.
     * @property packaging The packaging description.
     * @property storage Storage conditions.
     * @property sale Sale terms (e.g. over-the-counter or prescription).
     * @property shelfLife Shelf life duration.
     */
    data class DrugForm(
        val formName: String?,
        val dosage: String?,
        val packaging: String?,
        val storage: String?,
        val sale: String?,
        val shelfLife: String?
    )

    /**
     * Data class representing a paragraph/section in the drug instruction leaflet.
     *
     * @property title The title of the section.
     * @property text The description text of the section.
     */
    data class InstructionSection(
        val title: String?,
        val text: String?
    )
}
