package feo.health.ui.navigation

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