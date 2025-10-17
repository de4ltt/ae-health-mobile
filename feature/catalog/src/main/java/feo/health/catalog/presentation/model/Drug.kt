package feo.health.catalog.presentation.model

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
    data class DrugForm(
        val formName: String?,
        val dosage: String?,
        val packaging: String?,
        val storage: String?,
        val sale: String?,
        val shelfLife: String?
    )

    data class InstructionSection(
        val title: String?,
        val text: String?
    )
}
