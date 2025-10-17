package feo.health.catalog.clinic.api

import feo.health.catalog.clinic.dto.ClinicDto
import feo.health.catalog.doctor.dto.DoctorDto
import feo.health.network.endpoints.IApiEndpoints
import feo.health.network.model.NetworkResult
import feo.health.network.util.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.parameters
import javax.inject.Inject

internal class ClinicApi @Inject constructor(
    private val httpClient: HttpClient,
    private val clinicEndpoints: IApiEndpoints.Catalog.Clinic
) : IClinicApi {

    override suspend fun getClinics(
        q: String,
        isLocated: Boolean
    ): NetworkResult<List<ClinicDto>> = RequestHandler.handle {
        httpClient.get(clinicEndpoints.CLINICS) {
            parameters {
                "q" to q
                "located" to isLocated
            }
        }.body()
    }

    override suspend fun getClinicsByType(link: String): NetworkResult<List<ClinicDto>> =
        RequestHandler.handle {
            val url: String = clinicEndpoints.CLINICS_BY_TYPE.replace("{link}", link)
            httpClient.get(url).body()
        }

    override suspend fun getClinicInfo(link: String, isLocated: Boolean): NetworkResult<ClinicDto> =
        RequestHandler.handle {
            val url: String = clinicEndpoints.CLINIC.replace("{link}", link)
            httpClient.get(url).body()
        }

    override suspend fun getClinicDoctors(link: String): NetworkResult<List<DoctorDto>> =
        RequestHandler.handle {
            val url: String = clinicEndpoints.CLINIC_DOCTORS.replace("{link}", link)
            httpClient.get(url).body()
        }
}