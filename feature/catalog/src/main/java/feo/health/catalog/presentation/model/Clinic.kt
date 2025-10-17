package feo.health.catalog.presentation.model

import feo.health.catalog.domain.model.ReviewDomain
import feo.health.catalog.presentation.model.util.CatalogItemType

data class Clinic(
    val name: String,
    val link: String,
    val address: String?,
    val phoneNumber: String?,
    val imageUri: String?,
    val reviews: List<Review>?
)
