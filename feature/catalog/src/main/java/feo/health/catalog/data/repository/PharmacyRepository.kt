package feo.health.catalog.data.repository

import feo.health.catalog.data.mapper.PharmacyDtoToPharmacyDomainMapper.toPharmacyDomain
import feo.health.catalog.data.mapper.PharmacyDtoToPharmacyDomainMapper.toPharmacyDomainList
import feo.health.catalog.data.mapper.PharmacyDtoToPharmacyDomainMapper.toPharmacyDto
import feo.health.catalog.domain.model.PharmacyDomain
import feo.health.catalog.domain.repository.IPharmacyRepository
import feo.health.catalog.pharmacy.api.IPharmacyApi
import feo.health.network.model.mapResult
import javax.inject.Inject

class PharmacyRepository @Inject constructor(
    private val pharmacyApi: IPharmacyApi
) : IPharmacyRepository {

    override suspend fun getPharmacies(
        lat: Double,
        lon: Double,
        radius: Int
    ): List<PharmacyDomain> = pharmacyApi
        .getPharmacies(lat = lat, lon = lon, radius = radius)
        .mapResult { it.toPharmacyDomainList() }

    override suspend fun visitPharmacy(pharmacyDomain: PharmacyDomain) = pharmacyApi
        .visitPharmacy(pharmacyDomain.toPharmacyDto())
        .mapResult { it }

    override suspend fun getPharmacyById(id: Long): PharmacyDomain = pharmacyApi
        .getPharmacyById(id = id)
        .mapResult { it.toPharmacyDomain() }
}