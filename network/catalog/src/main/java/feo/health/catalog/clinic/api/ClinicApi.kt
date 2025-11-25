package feo.health.catalog.clinic.api

import feo.health.catalog.Mock
import feo.health.catalog.clinic.dto.ClinicDto
import feo.health.catalog.doctor.dto.DoctorDto
import feo.health.network.endpoints.ApiEndpoints
import feo.health.network.model.NetworkResult
import feo.health.network.util.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.parameters
import javax.inject.Inject

internal class ClinicApi @Inject constructor(
    private val httpClient: HttpClient
) : IClinicApi {

    override suspend fun getClinics(
        q: String,
        isLocated: Boolean
    ): NetworkResult<List<ClinicDto>> = RequestHandler.handle {
        httpClient.get(ApiEndpoints.Catalog.Clinic.GET_CLINICS) {
            parameter("q", q)
            parameter("located", isLocated)
        }.body()
    }

    override suspend fun getClinicsByType(link: String): NetworkResult<List<ClinicDto>> =
        RequestHandler.handle {
            val url: String =
                ApiEndpoints.Catalog.Clinic.GET_CLINICS_BY_TYPE.replace("{link}", link)
            httpClient.get(url).body()
        }

    override suspend fun getClinicInfo(link: String, isLocated: Boolean): NetworkResult<ClinicDto> =
        RequestHandler.handle {
            val url: String = ApiEndpoints.Catalog.Clinic.GET_CLINIC.replace("{link}", link)
            httpClient.get(url).body()
        }

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
