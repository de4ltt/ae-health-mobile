package feo.health.catalog.presentation.model

data class Service(
    val name: String,
    val link: String,
    val itemType: String = "service"
)
