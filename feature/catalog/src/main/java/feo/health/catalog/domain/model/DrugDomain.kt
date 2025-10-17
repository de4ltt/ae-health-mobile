package feo.health.catalog.domain.model

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
    data class DrugFormDomain(
        val formName: String?,
        val dosage: String?,
        val packaging: String?,
        val storage: String?,
        val sale: String?,
        val shelfLife: String?
    )

    data class InstructionSectionDomain(
        val title: String?,
        val text: String?
    )
}
