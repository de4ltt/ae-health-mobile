package feo.health.catalog.domain.repository

import feo.health.catalog.domain.model.DrugDomain

interface IDrugRepository {
    suspend fun getDrugs(q: String): List<DrugDomain>
    suspend fun getDrugInfo(link: String): DrugDomain
}