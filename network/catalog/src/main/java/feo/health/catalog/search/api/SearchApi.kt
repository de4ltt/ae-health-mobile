package feo.health.catalog.search.api

import feo.health.catalog.search.dto.CoordsDto
import feo.health.catalog.search.dto.SearchDto
import feo.health.network.endpoints.ApiEndpoints
import feo.health.network.model.NetworkResult
import feo.health.network.util.RequestHandler
import feo.health.secrets.HSecrets
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.parameters
import java.net.URLEncoder
import javax.inject.Inject

/**
 * API implementation managing search directory matching and address geocoding resolution.
 *
 * @property httpClient Network HTTP client provider.
 * @property secrets Core decrypted secrets credentials store.
 */
internal class SearchApi @Inject constructor(
    private val httpClient: HttpClient,
    private val secrets: HSecrets
) : ISearchApi {

    /**
     * Queries the catalog search endpoint for clinics, doctors, and services matching query key.
     *
     * @param q Match keyword.
     * @param isLocated Filter coordinates boundary active location.
     * @return [NetworkResult] wrapping matching [SearchDto] containing matches.
     */
    override suspend fun search(
        q: String,
        isLocated: Boolean
    ): NetworkResult<SearchDto> =
        RequestHandler.handle {
            httpClient.get(ApiEndpoints.Catalog.GET_SEARCH) {
                parameters {
                    parameter("q", q)
                    parameter("located", isLocated)
                }
            }.body()
        }

    /**
     * Resolves physical coordinate details by query address string using 2GIS API geolocation decoder.
     *
     * @param address Full address description.
     * @return [NetworkResult] wrapping geocoded [CoordsDto] latitude and longitude.
     */
    override suspend fun getCoordsForAddress(address: String): NetworkResult<CoordsDto> =
        RequestHandler.handle {
            val encodedAddress = URLEncoder.encode(
                if (!address.contains("г. "))
                    "г. Краснодар, $address"
                else address, "UTF-8"
            )
            val key = secrets.twoGISApiKey
            val url =
                "https://catalog.api.2gis.com/3.0/items/geocode?q=$encodedAddress&fields=items.point&key=$key"
            val response = httpClient.get(url) {
                header("Content-Type", "Application/Json")
            }.bodyAsText()
            CoordsDto.extractCoords(response)
        }
}
/*
internal class SearchApi @Inject constructor(
    private val httpClient: HttpClient
) : ISearchApi {

    override suspend fun search(
        q: String,
        isLocated: Boolean
    ): NetworkResult<SearchDto> =
        NetworkResult.Success(SearchDto(
            doctors = Mock.doctors,
            clinics = Mock.clinics,
            services = Mock.services
        ))
}*/
