package feo.health.catalog.pharmacy.dto

import kotlinx.serialization.Serializable

@Serializable
data class PharmacyDto(
    val name: String?,
    val phoneNumber: String?,
    val website: String?,
    val address: String?,
    val openingHours: List<String>
)
