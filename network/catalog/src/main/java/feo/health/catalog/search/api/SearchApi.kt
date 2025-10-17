package feo.health.catalog.search.api

import feo.health.catalog.search.dto.SearchDto
import feo.health.network.endpoints.IApiEndpoints
import feo.health.network.model.NetworkResult
import feo.health.network.util.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.parameters
import io.ktor.util.logging.Logger
import javax.inject.Inject

internal class SearchApi @Inject constructor(
    private val httpClient: HttpClient,
    private val searchEndpoints: IApiEndpoints.Catalog.Search
) : ISearchApi {

    override suspend fun search(
        q: String,
        isLocated: Boolean
    ): NetworkResult<SearchDto> =
        RequestHandler.handle {
            httpClient.get(searchEndpoints.SEARCH) {
                parameters {
                    "q" to q
                    "located" to isLocated
                }
            }.body()
        }
}