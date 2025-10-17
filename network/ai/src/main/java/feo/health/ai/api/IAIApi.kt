package feo.health.ai.api

import feo.health.ai.dto.request.DiseaseRequest
import feo.health.ai.dto.request.ProcedureRequest
import feo.health.ai.dto.request.SuggestionRequest
import feo.health.ai.dto.response.DiseaseResponse
import feo.health.ai.dto.response.ProcedureResponse
import feo.health.ai.dto.response.SuggestionResponse
import feo.health.network.model.NetworkResult

interface IAIApi {
    suspend fun getSuggestion(suggestionRequest: SuggestionRequest): NetworkResult<SuggestionResponse>
    suspend fun getDisease(diseaseRequest: DiseaseRequest): NetworkResult<DiseaseResponse>
    suspend fun getProcedureInfo(procedureRequest: ProcedureRequest): NetworkResult<ProcedureResponse>
}