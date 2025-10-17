package feo.health.catalog.domain.model

data class PharmacyDomain(
    val name: String?,
    val phoneNumber: String?,
    val website: String?,
    val address: String?,
    val openingHours: List<String>
)
