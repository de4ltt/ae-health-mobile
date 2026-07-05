package feo.health.catalog.domain.model

/**
 * Domain representation of a Pharmacy.
 *
 * This data class contains information about a pharmacy, including its name, contact
 * numbers, website, physical address, and hours of operation.
 *
 * @property name The name of the pharmacy, if available.
 * @property phoneNumber The contact phone number, if available.
 * @property website The official website URL, if available.
 * @property address The physical location/address of the pharmacy, if available.
 * @property openingHours The list of opening hours or working schedules.
 */
data class PharmacyDomain(
    val name: String?,
    val phoneNumber: String?,
    val website: String?,
    val address: String?,
    val openingHours: List<String>
)
