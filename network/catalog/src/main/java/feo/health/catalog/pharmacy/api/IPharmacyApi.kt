package feo.health.catalog.pharmacy.api

import feo.health.catalog.pharmacy.dto.PharmacyDto
import feo.health.network.model.NetworkResult

interface IPharmacyApi {
    suspend fun getPharmacies(lat: Double, lon: Double, radius: Int): NetworkResult<List<PharmacyDto>>
    suspend fun visitPharmacy(pharmacyDto: PharmacyDto): NetworkResult<Unit>
    suspend fun getPharmacyById(id: Long): NetworkResult<PharmacyDto>
}