package feo.health.network.endpoints.impl

import feo.health.common.model.datastore.IpStorage
import feo.health.network.endpoints.IApiEndpoints
import io.github.cdimascio.dotenv.Dotenv
import javax.inject.Inject

internal class SearchEndpoints @Inject constructor(
    private val dotenv: Dotenv,
    private val ipStorage: IpStorage
): IApiEndpoints.Catalog.Search {
    override val SEARCH: String = "${ipStorage.ip}" + dotenv.get("SEARCH")
}