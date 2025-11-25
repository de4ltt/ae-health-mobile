package feo.health.ai.domain.repository

import feo.health.ai.domain.model.request.FeatureDiseaseRequestDomain
import feo.health.ai.domain.model.request.FeatureProcedureRequestDomain
import feo.health.ai.domain.model.request.FeatureSuggestionRequestDomain
import feo.health.ai.domain.model.response.FeatureDiseaseResponseDomain
import feo.health.ai.domain.model.response.FeatureProcedureResponseDomain
import feo.health.ai.domain.model.response.FeatureSuggestionResponseDomain

interface IAiRepository {
    suspend fun getSuggestion(suggestionRequest: FeatureSuggestionRequestDomain): FeatureSuggestionResponseDomain
    suspend fun getDisease(diseaseRequest: FeatureDiseaseRequestDomain): FeatureDiseaseResponseDomain
    suspend fun getProcedureInfo(procedureRequest: FeatureProcedureRequestDomain): FeatureProcedureResponseDomain
}