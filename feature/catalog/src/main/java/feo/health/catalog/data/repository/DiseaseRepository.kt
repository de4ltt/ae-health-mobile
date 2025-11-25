package feo.health.catalog.data.repository

import feo.health.catalog.data.mapper.DiseaseDtoToDiseaseDomainMapper.toDiseaseDomainList
import feo.health.catalog.disease.api.IDiseaseApi
import feo.health.catalog.domain.model.DiseaseDomain
import feo.health.catalog.domain.repository.IDiseaseRepository
import feo.health.network.model.mapResult
import javax.inject.Inject

class DiseaseRepository @Inject constructor(
    private val diseaseApi: IDiseaseApi
) : IDiseaseRepository {

    override suspend fun getDiseases(q: String): List<DiseaseDomain> = diseaseApi
        .getDiseases(q = q).mapResult { it.toDiseaseDomainList() }

    override suspend fun getDiseaseInfo(link: String): String = diseaseApi
        .getDiseaseInfo(link = link).mapResult { it }
}