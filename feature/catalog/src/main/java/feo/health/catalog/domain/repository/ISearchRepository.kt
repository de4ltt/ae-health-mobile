package feo.health.catalog.domain.repository

import feo.health.catalog.domain.model.CoordsDomain
import feo.health.catalog.domain.model.SearchDomain

/**
 * Repository interface for handling catalog-wide search operations.
 *
 * This repository defines methods for performing general searches and geocoding addresses to coordinates.
 */
interface ISearchRepository {
    /**
     * Performs a general search for clinics, doctors, and services matching a query.
     *
     * @param q The search query string.
     * @param isLocated Flag indicating whether results should be filtered/sorted by user location.
     * @return The aggregated search results as a [SearchDomain].
     */
    suspend fun search(q: String, isLocated: Boolean): SearchDomain

    /**
     * Geocodes a text address into latitude and longitude coordinates.
     *
     * @param address The physical address to geocode.
     * @return The coordinates of the address as a [CoordsDomain].
     */
    suspend fun getCoordsForAddress(address: String): CoordsDomain
}