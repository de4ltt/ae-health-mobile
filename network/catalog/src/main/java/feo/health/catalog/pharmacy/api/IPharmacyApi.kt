package feo.health.catalog.pharmacy.api

import feo.health.catalog.pharmacy.dto.PharmacyDto
import feo.health.network.model.NetworkResult

/**
 * API contract for searching pharmacy directories and logging check-in entries.
 */
interface IPharmacyApi {
    /**
     * Fetch list of pharmacies located within coordinate radius.
     *
     * @param lat Center latitude.
     * @param lon Center longitude.
     * @param radius Bound query radius in meters.
     * @return [NetworkResult] wrapping pharmacy list.
     */
    suspend fun getPharmacies(lat: Double, lon: Double, radius: Int): NetworkResult<List<PharmacyDto>>

    /**
     * Log user checking in to specific pharmacy location.
     *
     * @param pharmacyDto The checked-in pharmacy data.
     * @return [NetworkResult] wrapping check-in task status.
     */
    suspend fun visitPharmacy(pharmacyDto: PharmacyDto): NetworkResult<Unit>

    /**
     * Fetch detail entry information of specific pharmacy.
     *
     * @param id The pharmacy ID.
     * @return [NetworkResult] wrapping pharmacy DTO details.
     */
    suspend fun getPharmacyById(id: Long): NetworkResult<PharmacyDto>
}