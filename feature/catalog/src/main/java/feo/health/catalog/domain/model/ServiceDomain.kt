package feo.health.catalog.domain.model

data class ServiceDomain(
    val name: String,
    val link: String,
    val itemType: String = "service"
)
