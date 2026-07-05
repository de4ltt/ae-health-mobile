package feo.health.catalog.domain.repository

import feo.health.catalog.domain.model.PharmacyDomain

/**
 * Repository interface for managing and retrieving pharmacy-related data.
 *
 * This repository defines operations to search for pharmacies nearby, track pharmacy visits,
 * and retrieve a specific pharmacy by its identifier.
 */
interface IPharmacyRepository {
    /**
     * Retrieves a list of pharmacies near geographic coordinates within a specified radius.
     *
     * @param lat The latitude coordinates of the search location.
     * @param lon The longitude coordinates of the search location.
     * @param radius The radius limit for searching pharmacies, in meters or kilometers.
     * @return A list of [PharmacyDomain] objects matching the location criteria.
     */
    suspend fun getPharmacies(lat: Double, lon: Double, radius: Int): List<PharmacyDomain>

    /**
     * Registers or records a visit to a pharmacy.
     *
     * @param pharmacyDomain The pharmacy being visited.
     */
    suspend fun visitPharmacy(pharmacyDomain: PharmacyDomain): Unit

    /**
     * Retrieves detailed information about a specific pharmacy by its ID.
     *
     * @param id The unique identifier of the pharmacy.
     * @return The [PharmacyDomain] matching the given ID.
     */
    suspend fun getPharmacyById(id: Long): PharmacyDomain
}