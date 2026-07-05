package feo.health.ai.data.repository

import feo.health.ai.api.IAIApi
import feo.health.ai.data.mapper.DiseaseRequestToFeatureDiseaseRequestDomainMapper.toDiseaseRequest
import feo.health.ai.data.mapper.DiseaseResponseToFeatureDiseaseResponseDomainMapper.toFeatureDomain
import feo.health.ai.data.mapper.ProcedureRequestToFeatureProcedureRequestDomainMapper.toProcedureRequest
import feo.health.ai.data.mapper.ProcedureResponseToFeatureProcedureResponseDomainMapper.toFeatureDomain
import feo.health.ai.data.mapper.SuggestionRequestToFeatureSuggestionRequestDomainMapper.toSuggestionRequest
import feo.health.ai.data.mapper.SuggestionResponseToFeatureSuggestionResponseDomainMapper.toFeatureDomain
import feo.health.ai.domain.model.request.FeatureDiseaseRequestDomain
import feo.health.ai.domain.model.request.FeatureProcedureRequestDomain
import feo.health.ai.domain.model.request.FeatureSuggestionRequestDomain
import feo.health.ai.domain.model.response.FeatureDiseaseResponseDomain
import feo.health.ai.domain.model.response.FeatureProcedureResponseDomain
import feo.health.ai.domain.model.response.FeatureSuggestionResponseDomain
import feo.health.ai.domain.repository.IAiRepository
import feo.health.network.model.mapResult
import javax.inject.Inject

/**
 * Repository interface implementation managing remote AI suggest recommendations and diagnostics analyses queries.
 *
 * @property aiApi Remote AI services search API client.
 */
class AiRepository @Inject constructor(
    private val aiApi: IAIApi
) : IAiRepository {

    /**
     * Queries matching suggest recommendations.
     *
     * @param suggestionRequest Dynamic recommendation queries configurations parameters.
     * @return Domain [FeatureSuggestionResponseDomain] suggest matched details.
     */
    override suspend fun getSuggestion(suggestionRequest: FeatureSuggestionRequestDomain): FeatureSuggestionResponseDomain =
        aiApi.getSuggestion(suggestionRequest.toSuggestionRequest()).mapResult { it.toFeatureDomain() }

    /**
     * Submits symptom description properties to query probable diagnostics matching.
     *
     * @param diseaseRequest Symptom match description queries parameters.
     * @return Domain [FeatureDiseaseResponseDomain] diagnostics metrics.
     */
    override suspend fun getDisease(diseaseRequest: FeatureDiseaseRequestDomain): FeatureDiseaseResponseDomain =
        aiApi.getDisease(diseaseRequest.toDiseaseRequest()).mapResult { it.toFeatureDomain() }

    /**
     * Queries detailed specs properties of specific medical procedure.
     *
     * @param procedureRequest Medical service procedure name query properties.
     * @return Domain [FeatureProcedureResponseDomain] detailed service procedure specifications.
     */
    override suspend fun getProcedureInfo(procedureRequest: FeatureProcedureRequestDomain): FeatureProcedureResponseDomain =
        aiApi.getProcedureInfo(procedureRequest.toProcedureRequest()).mapResult { it.toFeatureDomain() }
}