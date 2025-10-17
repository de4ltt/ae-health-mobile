package feo.health.catalog.doctor.api

import feo.health.catalog.doctor.dto.DoctorDto
import feo.health.network.endpoints.IApiEndpoints
import feo.health.network.model.NetworkResult
import feo.health.network.util.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

internal class DoctorApi @Inject constructor(
    private val httpClient: HttpClient,
    private val doctorEndpoints: IApiEndpoints.Catalog.Doctor
) : IDoctorApi {

    override suspend fun getDoctors(q: String): NetworkResult<List<DoctorDto>> =
        RequestHandler.handle {
            httpClient.get(doctorEndpoints.DOCTORS) {
                parameter("q", q)
            }.body()
        }

    override suspend fun getDoctorInfo(link: String): NetworkResult<DoctorDto> =
        RequestHandler.handle {
            val url: String = doctorEndpoints.DOCTOR.replace("{link}", link)
            httpClient.get(url).body()
        }

    override suspend fun getDoctorsBySpeciality(speciality: String): NetworkResult<List<DoctorDto>> =
        RequestHandler.handle {
            val url: String = doctorEndpoints.DOCTOR_SPECIALITY.replace("{link}", speciality)
            httpClient.get(url).body()
        }
}