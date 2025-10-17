package feo.health.ai.api

import feo.health.ai.dto.request.DiseaseRequest
import feo.health.ai.dto.request.ProcedureRequest
import feo.health.ai.dto.request.SuggestionRequest
import feo.health.ai.dto.response.DiseaseResponse
import feo.health.ai.dto.response.ProcedureResponse
import feo.health.ai.dto.response.SuggestionResponse
import feo.health.network.endpoints.IApiEndpoints
import feo.health.network.model.NetworkResult
import feo.health.network.util.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

internal class AIApi @Inject constructor(
    private val httpClient: HttpClient,
    private val aiEndpoints: IApiEndpoints.AI
): IAIApi {

    override suspend fun getSuggestion(suggestionRequest: SuggestionRequest): NetworkResult<SuggestionResponse> =
        RequestHandler.handle {
            httpClient.post(aiEndpoints.GET_SUGGESTION) {
                setBody(suggestionRequest)
            }.body()
        }

    override suspend fun getDisease(diseaseRequest: DiseaseRequest): NetworkResult<DiseaseResponse> =
        RequestHandler.handle {
            httpClient.post(aiEndpoints.GET_DISEASE) {
                setBody(diseaseRequest)
            }.body()
        }

    override suspend fun getProcedureInfo(procedureRequest: ProcedureRequest): NetworkResult<ProcedureResponse> =
        RequestHandler.handle {
            httpClient.post(aiEndpoints.GET_PROCEDURE) {
                setBody(procedureRequest)
            }.body()
        }
}