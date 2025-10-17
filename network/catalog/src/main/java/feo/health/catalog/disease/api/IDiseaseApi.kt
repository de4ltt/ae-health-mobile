package feo.health.catalog.disease.api

import feo.health.catalog.disease.dto.DiseaseDto
import feo.health.network.model.NetworkResult

interface IDiseaseApi {
    suspend fun getDiseases(q: String): NetworkResult<List<DiseaseDto>>
    suspend fun getDiseaseInfo(link: String): NetworkResult<String>
}