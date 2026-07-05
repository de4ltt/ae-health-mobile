package feo.health.catalog.clinic.api

import feo.health.catalog.clinic.dto.ClinicDto
import feo.health.catalog.doctor.dto.DoctorDto
import feo.health.network.endpoints.ApiEndpoints
import feo.health.network.model.NetworkResult
import feo.health.network.util.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

/**
 * API implementation managing clinic search queries and retrieval.
 *
 * @property httpClient Network HTTP client provider.
 */
internal class ClinicApi @Inject constructor(
    private val httpClient: HttpClient
) : IClinicApi {

    /**
     * Queries clinic list matching user search input text.
     *
     * @param q Text query parameters string.
     * @param isLocated Filter coordinates boundary active location.
     * @return [NetworkResult] wrapping list of matched [ClinicDto].
     */
    override suspend fun getClinics(
        q: String,
        isLocated: Boolean
    ): NetworkResult<List<ClinicDto>> = RequestHandler.handle {
        httpClient.get(ApiEndpoints.Catalog.Clinic.GET_CLINICS) {
            parameter("q", q)
            parameter("located", isLocated)
        }.body()
    }

    /**
     * Queries clinic list belonging to specific specialty category type.
     *
     * @param link Relative specialty navigation link parameter.
     * @return [NetworkResult] wrapping list of matched [ClinicDto].
     */
    override suspend fun getClinicsByType(link: String): NetworkResult<List<ClinicDto>> =
        RequestHandler.handle {
            val url: String =
                ApiEndpoints.Catalog.Clinic.GET_CLINICS_BY_TYPE.replace("{link}", link)
            httpClient.get(url).body()
        }

    /**
     * Queries details of a single clinic.
     *
     * @param link The clinic details URL key link.
     * @param isLocated Active geolocation flag.
     * @return [NetworkResult] wrapping matched [ClinicDto] details.
     */
    override suspend fun getClinicInfo(link: String, isLocated: Boolean): NetworkResult<ClinicDto> =
        RequestHandler.handle {
            val url: String = ApiEndpoints.Catalog.Clinic.GET_CLINIC.replace("{link}", link)
            httpClient.get(url).body()
        }

    /**
     * Queries list of doctors practicing at a clinic.
     *
     * @param link Clinic details key link.
     * @return [NetworkResult] wrapping matched doctor list.
     */
    override suspend fun getClinicDoctors(link: String): NetworkResult<List<DoctorDto>> =
        RequestHandler.handle {
            val url: String = ApiEndpoints.Catalog.Clinic.GET_CLINIC_DOCTORS.replace("{link}", link)
            httpClient.get(url).body()
        }
}
/*
internal class ClinicApi @Inject constructor(
    private val httpClient: HttpClient
) : IClinicApi {

    override suspend fun getClinics(
        q: String,
        isLocated: Boolean
    ): NetworkResult<List<ClinicDto>> =
        NetworkResult.Success(Mock.clinics)

    override suspend fun getClinicsByType(link: String): NetworkResult<List<ClinicDto>> =
        NetworkResult.Success(Mock.clinics)

    override suspend fun getClinicInfo(link: String, isLocated: Boolean): NetworkResult<ClinicDto> =
        NetworkResult.Success(Mock.clinics.random())

    override suspend fun getClinicDoctors(link: String): NetworkResult<List<DoctorDto>> =
        NetworkResult.Success(Mock.doctors.subList(Mock.doctors.size / 2, Mock.doctors.lastIndex))
}*/
