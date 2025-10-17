package feo.health.network.endpoints.impl

import feo.health.common.model.datastore.IpStorage
import feo.health.network.endpoints.IApiEndpoints
import io.github.cdimascio.dotenv.Dotenv
import javax.inject.Inject

internal class UserEndpoints @Inject constructor(
    private val dotenv: Dotenv,
    private val ipStorage: IpStorage
): IApiEndpoints.User {
    override val USER: String = "${ipStorage.ip}" + dotenv.get("USER")
    override val FAVOURITES: String = "${ipStorage.ip}" + dotenv.get("FAVOURITES")
    override val HISTORY: String = "${ipStorage.ip}" + dotenv.get("HISTORY")
    override val PASSWORD: String = "${ipStorage.ip}" + dotenv.get("PASSWORD")
}