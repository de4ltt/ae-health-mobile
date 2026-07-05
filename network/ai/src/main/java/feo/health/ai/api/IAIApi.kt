package feo.health.ai.api

import feo.health.ai.dto.request.DiseaseRequest
import feo.health.ai.dto.request.ProcedureRequest
import feo.health.ai.dto.request.SuggestionRequest
import feo.health.ai.dto.response.DiseaseResponse
import feo.health.ai.dto.response.ProcedureResponse
import feo.health.ai.dto.response.SuggestionResponse
import feo.health.network.model.NetworkResult

/**
 * API contract for fetching AI recommendations, diagnostics analyses, and medical procedure parameters.
 */
interface IAIApi {
    /**
     * Fetch smart recommendations suggestion list matching dynamic user input query.
     *
     * @param suggestionRequest Input request wrapping dynamic query string.
     * @return [NetworkResult] wrapping recommendation suggestions list details response DTO.
     */
    suspend fun getSuggestion(suggestionRequest: SuggestionRequest): NetworkResult<SuggestionResponse>

    /**
     * Analyze user symptom variables parameters to resolve possible matching diagnostics.
     *
     * @param diseaseRequest Input request containing symptom description lists.
     * @return [NetworkResult] wrapping probable disease match diagnostics metrics details response DTO.
     */
    suspend fun getDisease(diseaseRequest: DiseaseRequest): NetworkResult<DiseaseResponse>

    /**
     * Fetch detailed specs configurations for specific medical service procedure.
     *
     * @param procedureRequest Input request containing procedure key name query.
     * @return [NetworkResult] wrapping target procedure details response DTO.
     */
    suspend fun getProcedureInfo(procedureRequest: ProcedureRequest): NetworkResult<ProcedureResponse>
}