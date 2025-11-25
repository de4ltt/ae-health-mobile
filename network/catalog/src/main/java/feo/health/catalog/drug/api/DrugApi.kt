package feo.health.catalog.drug.api

import feo.health.catalog.drug.dto.DrugDto
import feo.health.network.endpoints.ApiEndpoints
import feo.health.network.model.NetworkResult
import feo.health.network.util.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

internal class DrugApi @Inject constructor(
    private val httpClient: HttpClient
) : IDrugApi {

    override suspend fun getDrugs(q: String): NetworkResult<List<DrugDto>> =
        RequestHandler.handle {
            httpClient.get(ApiEndpoints.Catalog.Drug.GET_DRUGS) {
                parameter("q", q)
            }.body()
        }

    override suspend fun getDrugInfo(link: String): NetworkResult<DrugDto> =
        RequestHandler.handle {
            val url: String = ApiEndpoints.Catalog.Drug.GET_DRUG.replace("{link}", link)
            httpClient.get(url).body()
        }
}