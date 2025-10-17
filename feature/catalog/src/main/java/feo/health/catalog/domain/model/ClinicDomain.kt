package feo.health.catalog.domain.model

data class ClinicDomain(
    val name: String,
    val link: String,
    val address: String?,
    val phoneNumber: String?,
    val imageUri: String?,
    val itemType: String,
    val reviews: List<ReviewDomain>?
)
