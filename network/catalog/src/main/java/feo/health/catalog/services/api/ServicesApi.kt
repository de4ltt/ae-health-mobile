package feo.health.catalog.services.api

import feo.health.catalog.clinic.dto.ClinicDto
import feo.health.catalog.services.dto.ServiceDto
import feo.health.network.endpoints.IApiEndpoints
import feo.health.network.model.NetworkResult
import feo.health.network.util.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

internal class ServicesApi @Inject constructor(
    private val httpClient: HttpClient,
    private val serviceEndpoints: IApiEndpoints.Catalog.Services
) : IServicesApi {

    override suspend fun getServices(q: String): NetworkResult<List<ServiceDto>> =
        RequestHandler.handle {
            httpClient.get(serviceEndpoints.SERVICES) {
                parameter("q", q)
            }.body()
        }

    override suspend fun getClinicsByService(link: String): NetworkResult<List<ClinicDto>> =
        RequestHandler.handle {
            val url: String = serviceEndpoints.CLINICS_BY_SERVICE.replace("{link}", link)
            httpClient.get(url).body()
        }
}