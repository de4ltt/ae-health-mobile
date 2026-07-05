package feo.health.catalog.pharmacy.api

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

/**
 * API implementation managing pharmacy check-ins, searches, and details retrieval.
 *
 * @property httpClient Network HTTP client provider.
 */
internal class PharmacyApi @Inject constructor(
    private val httpClient: HttpClient
) : IPharmacyApi {

    /**
     * Queries pharmacies located within a specific geocoordinate boundary radius.
     *
     * @param lat Center latitude.
     * @param lon Center longitude.
     * @param radius Boundary query distance in meters.
     * @return [NetworkResult] wrapping list of matched [PharmacyDto].
     */
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

    /**
     * Logs user visit/check-in action details.
     *
     * @param pharmacyDto The checked-in pharmacy data parameters.
     * @return [NetworkResult] signaling complete status.
     */
    override suspend fun visitPharmacy(pharmacyDto: PharmacyDto): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.post(ApiEndpoints.Catalog.Pharmacy.POST_VISIT_PHARMACY) {
                setBody(pharmacyDto)
            }.body()
        }

    /**
     * Queries details of a single pharmacy by identifier.
     *
     * @param id The pharmacy ID.
     * @return [NetworkResult] wrapping matched [PharmacyDto] details.
     */
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
