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

/**
 * API implementation managing disease searches and retrieval.
 *
 * @property httpClient Network HTTP client provider.
 */
internal class DiseaseApi @Inject constructor(
    private val httpClient: HttpClient
) : IDiseaseApi {

    /**
     * Queries disease list matching text keyword.
     *
     * @param q Text keyword.
     * @return [NetworkResult] wrapping list of matched [DiseaseDto].
     */
    override suspend fun getDiseases(q: String): NetworkResult<List<DiseaseDto>> =
        RequestHandler.handle {
            httpClient.get(ApiEndpoints.Catalog.Disease.GET_DISEASES) {
                parameter("q", q)
            }.body()
        }

    /**
     * Queries details/description of a single disease.
     *
     * @param link Relative disease navigation key.
     * @return [NetworkResult] wrapping detailed text description.
     */
    override suspend fun getDiseaseInfo(link: String): NetworkResult<String> =
        RequestHandler.handle {
            val url: String = ApiEndpoints.Catalog.Disease.GET_DISEASE.replace("{link}", link)
            httpClient.get(url).body()
        }
}