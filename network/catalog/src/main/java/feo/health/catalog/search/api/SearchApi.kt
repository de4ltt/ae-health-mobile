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
import io.ktor.http.Url
import io.ktor.http.parameters
import io.ktor.util.reflect.TypeInfo
import jdk.internal.reflect.ConstantPool
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder
import javax.inject.Inject

internal class SearchApi @Inject constructor(
    private val httpClient: HttpClient,
    private val secrets: HSecrets
) : ISearchApi {

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
