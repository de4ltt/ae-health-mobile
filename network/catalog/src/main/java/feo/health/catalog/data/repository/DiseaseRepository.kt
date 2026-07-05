package feo.health.catalog.data.repository

import feo.health.catalog.data.mapper.DiseaseDtoToDiseaseDomainMapper.toDomainList
import feo.health.catalog.disease.api.IDiseaseApi
import feo.health.catalog.domain.model.DiseaseDomain
import feo.health.catalog.domain.repository.IDiseaseRepository
import feo.health.network.model.mapResult
import javax.inject.Inject

/**
 * Repository interface implementation managing remote disease directory searches and detail retrievals.
 *
 * @property diseaseApi Remote disease search API service client.
 */
class DiseaseRepository @Inject constructor(
    private val diseaseApi: IDiseaseApi
) : IDiseaseRepository {

    /**
     * Queries diseases matching text keyword query.
     *
     * @param q Text keyword.
     * @return Domain list of matched [DiseaseDomain] diseases.
     */
    override suspend fun getDiseases(q: String): List<DiseaseDomain> = diseaseApi
        .getDiseases(q = q).mapResult { it.toDomainList() }

    /**
     * Queries detailed description info text of single disease.
     *
     * @param link Disease details key link.
     * @return Detailed info text summary.
     */
    override suspend fun getDiseaseInfo(link: String): String = diseaseApi
        .getDiseaseInfo(link = link).mapResult { it }
}