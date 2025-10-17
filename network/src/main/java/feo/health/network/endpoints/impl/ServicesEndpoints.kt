package feo.health.network.endpoints.impl

import feo.health.common.model.datastore.IpStorage
import feo.health.network.endpoints.IApiEndpoints
import io.github.cdimascio.dotenv.Dotenv
import javax.inject.Inject

internal class ServicesEndpoints @Inject constructor(
    private val dotenv: Dotenv,
    private val ipStorage: IpStorage
): IApiEndpoints.Catalog.Services {
    override val SERVICES: String = "${ipStorage.ip}" + dotenv.get("SERVICES")
    override val CLINICS_BY_SERVICE: String = "${ipStorage.ip}" + dotenv.get("CLINICS_BY_SERVICE")
}