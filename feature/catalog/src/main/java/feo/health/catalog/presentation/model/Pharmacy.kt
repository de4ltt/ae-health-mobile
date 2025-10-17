package feo.health.catalog.presentation.model

data class Pharmacy(
    val name: String?,
    val phoneNumber: String?,
    val website: String?,
    val address: String?,
    val openingHours: List<String>
)
