package feo.health.catalog.doctor.api

import feo.health.catalog.Mock
import feo.health.catalog.doctor.dto.DoctorDto
import feo.health.network.endpoints.ApiEndpoints
import feo.health.network.model.NetworkResult
import feo.health.network.util.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

internal class DoctorApi @Inject constructor(
    private val httpClient: HttpClient
) : IDoctorApi {

    override suspend fun getDoctors(q: String): NetworkResult<List<DoctorDto>> =
        RequestHandler.handle {
            httpClient.get(ApiEndpoints.Catalog.Doctor.GET_DOCTORS) {
                parameter("q", q)
            }.body<List<DoctorDto>>()
        }

    override suspend fun getDoctorInfo(link: String): NetworkResult<DoctorDto> =
        RequestHandler.handle {
            val url: String = ApiEndpoints.Catalog.Doctor.GET_DOCTOR.replace("{link}", link)
            val doctor = httpClient.get(url).body<DoctorDto>()
            doctor.copy(imageUri = doctor.imageUri?.replace("\\s+".toRegex(), ""))
        }

    override suspend fun getDoctorsBySpeciality(speciality: String): NetworkResult<List<DoctorDto>> =
        RequestHandler.handle {
            val url: String =
                ApiEndpoints.Catalog.Doctor.GET_DOCTOR_BY_SPECIALITY.replace("{link}", speciality)
            httpClient.get(url).body()
        }
}