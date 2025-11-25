package feo.health.catalog.pharmacy.api

import feo.health.catalog.Mock
import feo.health.catalog.pharmacy.dto.PharmacyDto
import feo.health.network.endpoints.ApiEndpoints
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
    private val httpClient: HttpClient
) : IPharmacyApi {

    override suspend fun getPharmacies(
        lat: Double,
        lon: Double,
        radius: Int
    ): NetworkResult<List<PharmacyDto>> = RequestHandler.handle {
        httpClient.get(ApiEndpoints.Catalog.Pharmacy.GET_PHARMACIES) {
            parameters {
                "lat" to lat
                "lon" to lon
                "radius" to radius
            }
        }.body()
    }

    override suspend fun visitPharmacy(pharmacyDto: PharmacyDto): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.post(ApiEndpoints.Catalog.Pharmacy.POST_VISIT_PHARMACY) {
                setBody(pharmacyDto)
            }.body()
        }

    override suspend fun getPharmacyById(id: Long): NetworkResult<PharmacyDto> =
        RequestHandler.handle {
            val url: String = ApiEndpoints.Catalog.Pharmacy.GET_PHARMACY.replace("{link}", "$id")
            httpClient.get(url).body()
        }
}
/*
internal class PharmacyApi @Inject constructor(
    private val httpClient: HttpClient
) : IPharmacyApi {

    override suspend fun getPharmacies(
        lat: Double,
        lon: Double,
        radius: Int
    ): NetworkResult<List<PharmacyDto>> =
        NetworkResult.Success(Mock.pharmacies)

    override suspend fun visitPharmacy(pharmacyDto: PharmacyDto): NetworkResult<Unit> =
        NetworkResult.Success(Unit)


    override suspend fun getPharmacyById(id: Long): NetworkResult<PharmacyDto> =
        NetworkResult.Success(Mock.pharmacies.random())

}*/
