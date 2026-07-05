package feo.health.ui.navigation

import kotlinx.serialization.Serializable

object Routes {
    val catalog
        get() = "catalog"
    val auth
        get() = "auth"
    val authLogOut
        get() = "$auth/log-out"
    val user
        get() = "user"
    val ai
        get() = "ai"
}

@Serializable
object CatalogRoute

@Serializable
data class CatalogDetailsRoute(val link: String, val type: String)

@Serializable
data class CatalogSpecialistsRoute(val link: String)

@Serializable
object AiRoute

@Serializable
object AuthRoute

@Serializable
object AuthLogOutRoute

@Serializable
object UserRoute

@Serializable
object UserFavouritesRoute

@Serializable
object UserHistoryRoute