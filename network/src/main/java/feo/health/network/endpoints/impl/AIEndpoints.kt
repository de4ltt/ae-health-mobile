package feo.health.network.endpoints.impl

import feo.health.common.model.datastore.IpStorage
import feo.health.network.endpoints.IApiEndpoints
import io.github.cdimascio.dotenv.Dotenv
import javax.inject.Inject

internal class AIEndpoints @Inject constructor(
    private val dotenv: Dotenv,
    private val ipStorage: IpStorage
): IApiEndpoints.AI {
    override val GET_SUGGESTION: String = "${ipStorage.ip}" + dotenv.get("GET_SUGGESTION")
    override val GET_PROCEDURE: String = "${ipStorage.ip}" + dotenv.get("GET_PROCEDURE")
    override val GET_DISEASE: String = "${ipStorage.ip}" + dotenv.get("GET_DISEASE")
}