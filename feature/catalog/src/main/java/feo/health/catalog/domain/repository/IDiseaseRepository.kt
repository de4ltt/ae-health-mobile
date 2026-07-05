package feo.health.catalog.domain.repository

import feo.health.catalog.domain.model.DiseaseDomain

/**
 * Repository interface for managing and retrieving disease-related data.
 *
 * This repository defines operations to query diseases and fetch specific disease descriptions or details.
 */
interface IDiseaseRepository {
    /**
     * Retrieves a list of diseases matching a search query.
     *
     * @param q The search query string.
     * @return A list of [DiseaseDomain] objects matching the query.
     */
    suspend fun getDiseases(q: String): List<DiseaseDomain>

    /**
     * Retrieves information/details about a specific disease as a String description.
     *
     * @param link The unique link/identifier of the disease.
     * @return A string containing detailed information about the disease.
     */
    suspend fun getDiseaseInfo(link: String): String
}