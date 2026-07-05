package feo.health.ai.api

import feo.health.ai.dto.request.DiseaseRequest
import feo.health.ai.dto.request.ProcedureRequest
import feo.health.ai.dto.request.SuggestionRequest
import feo.health.ai.dto.response.DiseaseResponse
import feo.health.ai.dto.response.ProcedureResponse
import feo.health.ai.dto.response.SuggestionResponse
import feo.health.network.endpoints.ApiEndpoints
import feo.health.network.model.NetworkResult
import feo.health.network.util.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

/**
 * API implementation managing AI diagnostic analysis, smart recommendation queries, and medical service specs.
 *
 * @property httpClient Network HTTP client provider.
 */
internal class AIApi @Inject constructor(
    private val httpClient: HttpClient
): IAIApi {

    /**
     * Queries remote AI service endpoints to resolve matching suggest recommendations list.
     *
     * @param suggestionRequest Dynamic recommendation queries configurations parameters.
     * @return [NetworkResult] wrapping list match suggestions.
     */
    override suspend fun getSuggestion(suggestionRequest: SuggestionRequest): NetworkResult<SuggestionResponse> =
        RequestHandler.handle {
            httpClient.post(ApiEndpoints.AI.POST_SUGGESTION) {
                setBody(suggestionRequest)
            }.body()
        }

    /**
     * Submits symptom description properties to query probable diagnostics matching.
     *
     * @param diseaseRequest Symptom match description queries parameters.
     * @return [NetworkResult] wrapping probable disease diagnostics.
     */
    override suspend fun getDisease(diseaseRequest: DiseaseRequest): NetworkResult<DiseaseResponse> =
        RequestHandler.handle {
            httpClient.post(ApiEndpoints.AI.POST_DISEASE) {
                setBody(diseaseRequest)
            }.body()
        }

    /**
     * Queries detailed specs properties of a specific medical procedure.
     *
     * @param procedureRequest Medical service procedure name query properties.
     * @return [NetworkResult] wrapping detailed service specs.
     */
    override suspend fun getProcedureInfo(procedureRequest: ProcedureRequest): NetworkResult<ProcedureResponse> =
        RequestHandler.handle {
            httpClient.post(ApiEndpoints.AI.POST_PROCEDURE) {
                setBody(procedureRequest)
            }.body()
        }
}