package feo.health.network.endpoints.impl

import feo.health.common.model.datastore.IpStorage
import feo.health.network.endpoints.IApiEndpoints
import io.github.cdimascio.dotenv.Dotenv
import javax.inject.Inject

internal class ClinicEndpoints @Inject constructor(
    private val dotenv: Dotenv,
    private val ipStorage: IpStorage
) : IApiEndpoints.Catalog.Clinic {
    override val CLINICS: String = "${ipStorage.ip}" + dotenv.get("CLINICS")
    override val CLINIC: String = "${ipStorage.ip}" + dotenv.get("CLINIC")
    override val CLINICS_BY_TYPE: String = "${ipStorage.ip}" + dotenv.get("CLINICS_BY_TYPE")
    override val CLINIC_DOCTORS: String = "${ipStorage.ip}" + dotenv.get("CLINIC_DOCTORS")
}