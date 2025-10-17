package feo.health.catalog.domain.repository

import feo.health.catalog.domain.model.DiseaseDomain

interface IDiseaseRepository {
    suspend fun getDiseases(q: String): List<DiseaseDomain>
    suspend fun getDiseaseInfo(link: String): String
}