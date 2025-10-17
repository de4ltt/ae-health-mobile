package feo.health.catalog.drug.dto

import kotlinx.serialization.Serializable

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
    @Serializable
    data class DrugFormDto(
        val formName: String?,
        val dosage: String?,
        val packaging: String?,
        val storage: String?,
        val sale: String?,
        val shelfLife: String?
    )

    @Serializable
    data class InstructionSectionDto(
        val title: String?,
        val text: String?
    )
}
