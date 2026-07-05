package feo.health.catalog.services.api

import feo.health.catalog.clinic.dto.ClinicDto
import feo.health.catalog.services.dto.ServiceDto
import feo.health.network.endpoints.ApiEndpoints
import feo.health.network.model.NetworkResult
import feo.health.network.util.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

/**
 * API implementation managing services list searches and related clinics retrieval.
 *
 * @property httpClient Network HTTP client provider.
 */
internal class ServicesApi @Inject constructor(
    private val httpClient: HttpClient
) : IServicesApi {

    /**
     * Queries services list matching text keyword.
     *
     * @param q Text keyword.
     * @return [NetworkResult] wrapping list of matched [ServiceDto].
     */
    override suspend fun getServices(q: String): NetworkResult<List<ServiceDto>> =
        RequestHandler.handle {
            httpClient.get(ApiEndpoints.Catalog.Services.GET_SERVICES) {
                parameter("q", q)
            }.body()
        }

    /**
     * Queries clinics providing a specific medical service.
     *
     * @param link Relative service navigation key link.
     * @return [NetworkResult] wrapping list of matched [ClinicDto].
     */
    override suspend fun getClinicsByService(link: String): NetworkResult<List<ClinicDto>> =
        RequestHandler.handle {
            val url: String = ApiEndpoints.Catalog.Services.GET_CLINICS_BY_SERVICE.replace("{link}", link)
            httpClient.get(url).body()
        }
}