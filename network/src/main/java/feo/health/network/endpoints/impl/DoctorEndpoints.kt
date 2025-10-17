package feo.health.network.endpoints.impl

import feo.health.common.model.datastore.IpStorage
import feo.health.network.endpoints.IApiEndpoints
import io.github.cdimascio.dotenv.Dotenv
import javax.inject.Inject

internal class DoctorEndpoints @Inject constructor(
    private val dotenv: Dotenv,
    private val ipStorage: IpStorage
): IApiEndpoints.Catalog.Doctor {
    override val DOCTORS: String = "${ipStorage.ip}" + dotenv.get("DOCTORS")
    override val DOCTOR_SPECIALITY: String = "${ipStorage.ip}" + dotenv.get("DOCTOR_SPECIALITY")
    override val DOCTOR: String = "${ipStorage.ip}" + dotenv.get("DOCTOR")
}