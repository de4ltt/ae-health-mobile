package feo.health.catalog.pharmacy.api

import feo.health.catalog.pharmacy.dto.PharmacyDto
import feo.health.network.endpoints.IApiEndpoints
import feo.health.network.model.NetworkResult
import feo.health.network.util.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.parameters
import javax.inject.Inject

internal class PharmacyApi @Inject constructor(
    private val httpClient: HttpClient,
    private val pharmacyEndpoints: IApiEndpoints.Catalog.Pharmacy
) : IPharmacyApi {

    override suspend fun getPharmacies(
        lat: Double,
        lon: Double,
        radius: Int
    ): NetworkResult<List<PharmacyDto>> = RequestHandler.handle {
        httpClient.get(pharmacyEndpoints.PHARMACIES) {
            parameters {
                "lat" to lat
                "lon" to lon
                "radius" to radius
            }
        }.body()
    }

    override suspend fun visitPharmacy(pharmacyDto: PharmacyDto): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.post(pharmacyEndpoints.VISIT_PHARMACY) {
                setBody(pharmacyDto)
            }.body()
        }

    override suspend fun getPharmacyById(id: Long): NetworkResult<PharmacyDto> =
        RequestHandler.handle {
            val url: String = pharmacyEndpoints.PHARMACY.replace("{link}", "$id")
            httpClient.get(url).body()
        }
}