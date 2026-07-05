package feo.health.catalog.doctor.api

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
 * API implementation managing doctor specialty list queries and retrieval.
 *
 * @property httpClient Network HTTP client provider.
 */
internal class DoctorApi @Inject constructor(
    private val httpClient: HttpClient
) : IDoctorApi {

    /**
     * Queries doctor list matching text keyword.
     *
     * @param q Text keyword.
     * @return [NetworkResult] wrapping list of matched [DoctorDto].
     */
    override suspend fun getDoctors(q: String): NetworkResult<List<DoctorDto>> =
        RequestHandler.handle {
            httpClient.get(ApiEndpoints.Catalog.Doctor.GET_DOCTORS) {
                parameter("q", q)
            }.body<List<DoctorDto>>()
        }

    /**
     * Queries details of a single doctor. Sanitizes image URL path string whitespaces.
     *
     * @param link Relative doctor navigation key link.
     * @return [NetworkResult] wrapping matched [DoctorDto] details.
     */
    override suspend fun getDoctorInfo(link: String): NetworkResult<DoctorDto> =
        RequestHandler.handle {
            val url: String = ApiEndpoints.Catalog.Doctor.GET_DOCTOR.replace("{link}", link)
            val doctor = httpClient.get(url).body<DoctorDto>()
            doctor.copy(imageUri = doctor.imageUri?.replace("\\s+".toRegex(), ""))
        }

    /**
     * Queries list of doctors belonging to a specific medical specialty.
     *
     * @param speciality Relative specialty navigation key link.
     * @return [NetworkResult] wrapping list of matched [DoctorDto].
     */
    override suspend fun getDoctorsBySpeciality(speciality: String): NetworkResult<List<DoctorDto>> =
        RequestHandler.handle {
            val url: String =
                ApiEndpoints.Catalog.Doctor.GET_DOCTOR_BY_SPECIALITY.replace("{link}", speciality)
            httpClient.get(url).body()
        }
}