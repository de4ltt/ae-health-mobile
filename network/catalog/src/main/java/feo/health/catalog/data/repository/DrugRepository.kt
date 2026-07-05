package feo.health.catalog.data.repository

import feo.health.catalog.data.mapper.DrugDtoToDrugDomainMapper.toDomain
import feo.health.catalog.data.mapper.DrugDtoToDrugDomainMapper.toDomainList
import feo.health.catalog.domain.model.DrugDomain
import feo.health.catalog.domain.repository.IDrugRepository
import feo.health.catalog.drug.api.IDrugApi
import feo.health.network.model.mapResult
import javax.inject.Inject

class DrugRepository @Inject constructor(
    private val drugApi: IDrugApi
) : IDrugRepository {

    override suspend fun getDrugs(q: String): List<DrugDomain> = drugApi
        .getDrugs(q = q)
        .mapResult { it.toDomainList() }

    override suspend fun getDrugInfo(link: String): DrugDomain = drugApi
        .getDrugInfo(link = link)
        .mapResult { it.toDomain() }
}