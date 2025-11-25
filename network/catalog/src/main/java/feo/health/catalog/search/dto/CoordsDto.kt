package feo.health.catalog.search.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.double
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class CoordsDto(
    val lon: Double,
    val lat: Double
) {
    companion object {
        fun extractCoords(json: String): CoordsDto {
            val root = Json.parseToJsonElement(json).jsonObject
            val items = root["result"]!!
                .jsonObject["items"]!!
                .jsonArray
            val point = items.first()
                .jsonObject["point"]!!
                .jsonObject

            val lat = point["lat"]!!.jsonPrimitive.double
            val lon = point["lon"]!!.jsonPrimitive.double

            return CoordsDto(lon = lon, lat = lat)
        }
    }
}
