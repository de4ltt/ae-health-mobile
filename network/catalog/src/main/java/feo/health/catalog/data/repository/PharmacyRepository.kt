package feo.health.catalog.data.repository

import feo.health.catalog.data.mapper.PharmacyDtoToPharmacyDomainMapper.toDomain
import feo.health.catalog.data.mapper.PharmacyDtoToPharmacyDomainMapper.toDomainList
import feo.health.catalog.data.mapper.PharmacyDtoToPharmacyDomainMapper.toDto
import feo.health.catalog.domain.model.PharmacyDomain
import feo.health.catalog.domain.repository.IPharmacyRepository
import feo.health.catalog.pharmacy.api.IPharmacyApi
import feo.health.network.model.mapResult
import javax.inject.Inject

/**
 * Repository interface implementation managing remote pharmacy searches, visits tracking, and details queries.
 *
 * @property pharmacyApi Remote pharmacy search API service client.
 */
class PharmacyRepository @Inject constructor(
    private val pharmacyApi: IPharmacyApi
) : IPharmacyRepository {

    /**
     * Queries pharmacies located within a coordinates boundary radius.
     *
     * @param lat Center latitude.
     * @param lon Center longitude.
     * @param radius Bound query radius in meters.
     * @return Domain list of matched [PharmacyDomain] pharmacies.
     */
    override suspend fun getPharmacies(
        lat: Double,
        lon: Double,
        radius: Int
    ): List<PharmacyDomain> = pharmacyApi
        .getPharmacies(lat = lat, lon = lon, radius = radius)
        .mapResult { it.toDomainList() }

    /**
     * Submits check-in details update action of user visiting specific pharmacy.
     *
     * @param pharmacyDomain Target visit domain entity information parameters.
     */
    override suspend fun visitPharmacy(pharmacyDomain: PharmacyDomain) = pharmacyApi
        .visitPharmacy(pharmacyDomain.toDto())
        .mapResult { it }

    /**
     * Queries detailed attributes of specific pharmacy by ID identifier.
     *
     * @param id The pharmacy ID.
     * @return Domain [PharmacyDomain] pharmacy entity detail specs.
     */
    override suspend fun getPharmacyById(id: Long): PharmacyDomain = pharmacyApi
        .getPharmacyById(id = id)
        .mapResult { it.toDomain() }
}