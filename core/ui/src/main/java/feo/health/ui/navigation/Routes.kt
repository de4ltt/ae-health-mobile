package feo.health.ui.navigation

import kotlinx.serialization.Serializable

/**
 * Root path string constants for application navigation.
 */
object Routes {
    /**
     * Home Catalog list graph path string.
     */
    val catalog
        get() = "catalog"

    /**
     * User registration/authentication graph path string.
     */
    val auth
        get() = "auth"

    /**
     * Logout action route path string.
     */
    val authLogOut
        get() = "$auth/log-out"

    /**
     * User profile settings graph path string.
     */
    val user
        get() = "user"

    /**
     * AI symptom-checking graph path string.
     */
    val ai
        get() = "ai"
}

/**
 * Type-safe target route representing the home catalog screen.
 */
@Serializable
object CatalogRoute

/**
 * Type-safe target route representing the organization details catalog screen.
 *
 * @property link The deep-link address or ID of the clinic/pharmacy.
 * @property type The catalog category type representation.
 */
@Serializable
data class CatalogDetailsRoute(val link: String, val type: String)

/**
 * Type-safe target route representing list of doctors within a specific specialty.
 *
 * @property link Deep-link path string of the specialization.
 */
@Serializable
data class CatalogSpecialistsRoute(val link: String)

/**
 * Type-safe target route representing the core AI checking screen.
 */
@Serializable
object AiRoute

/**
 * Type-safe target route representing the login screen.
 */
@Serializable
object AuthRoute

/**
 * Type-safe target route representing the logout confirmation modal.
 */
@Serializable
object AuthLogOutRoute

/**
 * Type-safe target route representing the user profile dashboard.
 */
@Serializable
object UserRoute

/**
 * Type-safe target route representing user's favorites directory screen.
 */
@Serializable
object UserFavouritesRoute

/**
 * Type-safe target route representing user's consult history logs.
 */
@Serializable
object UserHistoryRoute