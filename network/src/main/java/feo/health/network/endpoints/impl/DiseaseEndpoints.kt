package feo.health.network.endpoints.impl

import feo.health.common.model.datastore.IpStorage
import feo.health.network.endpoints.IApiEndpoints
import io.github.cdimascio.dotenv.Dotenv
import javax.inject.Inject

internal class DiseaseEndpoints @Inject constructor(
    private val dotenv: Dotenv,
    private val ipStorage: IpStorage
): IApiEndpoints.Catalog.Disease {
    override val DISEASES: String = "${ipStorage.ip}" + dotenv.get("DISEASES")
    override val DISEASE: String = "${ipStorage.ip}" + dotenv.get("DISEASE")
}