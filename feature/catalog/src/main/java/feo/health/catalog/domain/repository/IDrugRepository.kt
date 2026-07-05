package feo.health.catalog.domain.repository

import feo.health.catalog.domain.model.DrugDomain

/**
 * Repository interface for managing and retrieving drug-related data.
 *
 * This repository defines operations to search for pharmaceutical drugs and fetch specific drug information.
 */
interface IDrugRepository {
    /**
     * Retrieves a list of drugs matching a search query.
     *
     * @param q The search query string.
     * @return A list of [DrugDomain] objects matching the query.
     */
    suspend fun getDrugs(q: String): List<DrugDomain>

    /**
     * Retrieves detailed information about a specific drug.
     *
     * @param link The unique link/identifier of the drug.
     * @return The [DrugDomain] containing drug details.
     */
    suspend fun getDrugInfo(link: String): DrugDomain
}