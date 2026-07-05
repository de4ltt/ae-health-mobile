package feo.health.catalog.domain.model

/**
 * Domain representation of geographic coordinates.
 *
 * This data class is used to specify a location using latitude and longitude.
 *
 * @property lat The latitude of the location.
 * @property lon The longitude of the location.
 */
data class CoordsDomain(
    val lat: Double,
    val lon: Double
)
