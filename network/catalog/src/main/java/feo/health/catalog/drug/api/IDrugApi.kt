package feo.health.catalog.drug.api

import feo.health.catalog.drug.dto.DrugDto
import feo.health.network.model.NetworkResult

interface IDrugApi {
    suspend fun getDrugs(q: String): NetworkResult<List<DrugDto>>
    suspend fun getDrugInfo(link: String): NetworkResult<DrugDto>
}