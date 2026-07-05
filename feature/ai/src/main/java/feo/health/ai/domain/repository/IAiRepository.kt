package feo.health.ai.domain.repository

import feo.health.ai.domain.model.request.FeatureDiseaseRequestDomain
import feo.health.ai.domain.model.request.FeatureProcedureRequestDomain
import feo.health.ai.domain.model.request.FeatureSuggestionRequestDomain
import feo.health.ai.domain.model.response.FeatureDiseaseResponseDomain
import feo.health.ai.domain.model.response.FeatureProcedureResponseDomain
import feo.health.ai.domain.model.response.FeatureSuggestionResponseDomain

/**
 * Repository interface contract managing remote AI diagnostic queries.
 */
interface IAiRepository {
    /**
     * Fetch smart recommendation suggestions based on user query string.
     *
     * @param suggestionRequest Input request domain parameters.
     * @return Recommendation suggestion response domain details.
     */
    suspend fun getSuggestion(suggestionRequest: FeatureSuggestionRequestDomain): FeatureSuggestionResponseDomain

    /**
     * Query diagnostic potential disease matches matching user symptoms description.
     *
     * @param diseaseRequest Symptoms request domain parameters.
     * @return Probable disease diagnostics response domain details.
     */
    suspend fun getDisease(diseaseRequest: FeatureDiseaseRequestDomain): FeatureDiseaseResponseDomain

    /**
     * Query detailed properties about a medical service procedure.
     *
     * @param procedureRequest Service request domain parameters.
     * @return Medical service procedure response domain details.
     */
    suspend fun getProcedureInfo(procedureRequest: FeatureProcedureRequestDomain): FeatureProcedureResponseDomain
}