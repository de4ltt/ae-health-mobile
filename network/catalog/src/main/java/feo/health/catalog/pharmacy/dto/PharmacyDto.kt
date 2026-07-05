package feo.health.catalog.pharmacy.dto

import kotlinx.serialization.Serializable

/**
 * Data transfer object mapping pharmacy organization attributes.
 *
 * @property name Pharmacy name, if available.
 * @property phoneNumber Contact phone number, if available.
 * @property website URL endpoint link, if available.
 * @property address Pharmacy physical location address description.
 * @property openingHours Opening times rules split by week days.
 */
@Serializable
data class PharmacyDto(
    val name: String?,
    val phoneNumber: String?,
    val website: String?,
    val address: String?,
    val openingHours: List<String>
)
