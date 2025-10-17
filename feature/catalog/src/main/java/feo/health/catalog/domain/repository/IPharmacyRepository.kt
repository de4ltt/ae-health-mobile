package feo.health.catalog.domain.repository

import feo.health.catalog.domain.model.PharmacyDomain

interface IPharmacyRepository {
    suspend fun getPharmacies(lat: Double, lon: Double, radius: Int): List<PharmacyDomain>
    suspend fun visitPharmacy(pharmacyDomain: PharmacyDomain): Unit
    suspend fun getPharmacyById(id: Long): PharmacyDomain
}