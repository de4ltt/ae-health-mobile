package feo.health.ai.data.repository

import feo.health.ai.api.IAIApi
import feo.health.ai.data.mapper.DiseaseRequestToFeatureDiseaseRequestDomainMapper.toDiseaseRequest
import feo.health.ai.data.mapper.DiseaseResponseToFeatureDiseaseResponseDomainMapper.toFeatureDiseaseResponseDomain
import feo.health.ai.data.mapper.ProcedureRequestToFeatureProcedureRequestDomainMapper.toProcedureRequest
import feo.health.ai.data.mapper.ProcedureResponseToFeatureProcedureResponseDomainMapper.toFeatureProcedureResponseDomain
import feo.health.ai.data.mapper.SuggestionRequestToFeatureSuggestionRequestDomainMapper.toSuggestionRequest
import feo.health.ai.data.mapper.SuggestionResponseToFeatureSuggestionResponseDomainMapper.toFeatureSuggestionResponseDomain
import feo.health.ai.domain.model.request.FeatureDiseaseRequestDomain
import feo.health.ai.domain.model.request.FeatureProcedureRequestDomain
import feo.health.ai.domain.model.request.FeatureSuggestionRequestDomain
import feo.health.ai.domain.model.response.FeatureDiseaseResponseDomain
import feo.health.ai.domain.model.response.FeatureProcedureResponseDomain
import feo.health.ai.domain.model.response.FeatureSuggestionResponseDomain
import feo.health.ai.domain.repository.IAiRepository
import feo.health.network.model.mapResult
import javax.inject.Inject

class AiRepository @Inject constructor(
    private val aiApi: IAIApi
) : IAiRepository {

    override suspend fun getSuggestion(suggestionRequest: FeatureSuggestionRequestDomain): FeatureSuggestionResponseDomain =
        aiApi.getSuggestion(suggestionRequest.toSuggestionRequest()).mapResult { it.toFeatureSuggestionResponseDomain() }

    override suspend fun getDisease(diseaseRequest: FeatureDiseaseRequestDomain): FeatureDiseaseResponseDomain =
        aiApi.getDisease(diseaseRequest.toDiseaseRequest()).mapResult { it.toFeatureDiseaseResponseDomain() }

    override suspend fun getProcedureInfo(procedureRequest: FeatureProcedureRequestDomain): FeatureProcedureResponseDomain =
        aiApi.getProcedureInfo(procedureRequest.toProcedureRequest()).mapResult { it.toFeatureProcedureResponseDomain() }
}