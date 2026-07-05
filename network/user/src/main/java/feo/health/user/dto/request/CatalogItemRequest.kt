package feo.health.user.dto.request

import kotlinx.serialization.Serializable

/**
 * Data transfer request object representing a catalog item change action (e.g. favourite toggle, history check).
 *
 * @property type Item catalog category type (e.g. doctor, clinic).
 * @property link Navigational route identifier link.
 */
@Serializable
data class CatalogItemRequest(
    val type: String,
    val link: String?
)