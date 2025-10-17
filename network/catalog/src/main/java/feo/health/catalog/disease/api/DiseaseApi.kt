package feo.health.catalog.disease.api

import feo.health.catalog.disease.dto.DiseaseDto
import feo.health.network.endpoints.IApiEndpoints
import feo.health.network.model.NetworkResult
import feo.health.network.util.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

internal class DiseaseApi @Inject constructor(
    private val httpClient: HttpClient,
    private val diseaseEndpoints: IApiEndpoints.Catalog.Disease
) : IDiseaseApi {

    override suspend fun getDiseases(q: String): NetworkResult<List<DiseaseDto>> =
        RequestHandler.handle {
            httpClient.get(diseaseEndpoints.DISEASES) {
                parameter("q", q)
            }.body()
        }

    override suspend fun getDiseaseInfo(link: String): NetworkResult<String> =
        RequestHandler.handle {
            val url: String = diseaseEndpoints.DISEASE.replace("{link}", link)
            httpClient.get(url).body()
        }
}