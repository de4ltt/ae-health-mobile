package feo.health.network.endpoints.impl

import feo.health.common.model.datastore.IpStorage
import feo.health.network.endpoints.IApiEndpoints
import io.github.cdimascio.dotenv.Dotenv
import javax.inject.Inject

internal class PharmacyEndpoints @Inject constructor(
    private val dotenv: Dotenv,
    private val ipStorage: IpStorage
): IApiEndpoints.Catalog.Pharmacy {
    override val PHARMACIES: String = "${ipStorage.ip}" + dotenv.get("PHARMACIES")
    override val VISIT_PHARMACY: String = "${ipStorage.ip}" + dotenv.get("VISIT_PHARMACY")
    override val PHARMACY: String = "${ipStorage.ip}" + dotenv.get("PHARMACY")
}