package feo.health.catalog.disease.api

import feo.health.catalog.disease.dto.DiseaseDto
import feo.health.network.endpoints.ApiEndpoints
import feo.health.network.model.NetworkResult
import feo.health.network.util.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

internal class DiseaseApi @Inject constructor(
    private val httpClient: HttpClient
) : IDiseaseApi {

    override suspend fun getDiseases(q: String): NetworkResult<List<DiseaseDto>> =
        RequestHandler.handle {
            httpClient.get(ApiEndpoints.Catalog.Disease.GET_DISEASES) {
                parameter("q", q)
            }.body()
        }

    override suspend fun getDiseaseInfo(link: String): NetworkResult<String> =
        RequestHandler.handle {
            val url: String = ApiEndpoints.Catalog.Disease.GET_DISEASE.replace("{link}", link)
            httpClient.get(url).body()
        }
}