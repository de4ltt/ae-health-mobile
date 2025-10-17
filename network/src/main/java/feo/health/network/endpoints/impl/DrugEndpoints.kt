package feo.health.network.endpoints.impl

import feo.health.common.model.datastore.IpStorage
import feo.health.network.endpoints.IApiEndpoints
import io.github.cdimascio.dotenv.Dotenv
import javax.inject.Inject

internal class DrugEndpoints @Inject constructor(
    private val dotenv: Dotenv,
    private val ipStorage: IpStorage
) : IApiEndpoints.Catalog.Drug {
    override val DRUGS: String = "${ipStorage.ip}" + dotenv.get("DRUGS")
    override val DRUG: String = "${ipStorage.ip}" + dotenv.get("DRUG")
}