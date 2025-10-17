package feo.health.network.endpoints.impl

import feo.health.common.model.datastore.IpStorage
import feo.health.network.endpoints.IApiEndpoints
import io.github.cdimascio.dotenv.Dotenv
import javax.inject.Inject

internal class AuthEndpoints @Inject constructor(
    private val dotenv: Dotenv,
    private val ipStorage: IpStorage,
) : IApiEndpoints.Auth {
    override val SIGN_IN: String = "${ipStorage.ip}" + dotenv.get("SIGN_IN")
    override val SIGN_UP: String = "${ipStorage.ip}" + dotenv.get("SIGN_UP")
    override val REFRESH_TOKEN: String = "${ipStorage.ip}" + dotenv.get("REFRESH_TOKEN")
}