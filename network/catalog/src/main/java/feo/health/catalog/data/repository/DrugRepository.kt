package feo.health.catalog.data.repository

import feo.health.catalog.data.mapper.DrugDtoToDrugDomainMapper.toDomain
import feo.health.catalog.data.mapper.DrugDtoToDrugDomainMapper.toDomainList
import feo.health.catalog.domain.model.DrugDomain
import feo.health.catalog.domain.repository.IDrugRepository
import feo.health.catalog.drug.api.IDrugApi
import feo.health.network.model.mapResult
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

/**
 * Repository interface implementation managing remote drug medication searches and details queries.
 *
 * @property drugApi Remote drug search API service client.
 */
class DrugRepository @Inject constructor(
    private val drugApi: IDrugApi
) : IDrugRepository {

    private val infoCache = RepositoryCache<String, DrugDomain>(100)
    private val mutex = Mutex()

    /**
     * Queries drug medications matching text keyword query.
     *
     * @param q Text keyword.
     * @return Domain list of matched [DrugDomain] medications.
     */
    override suspend fun getDrugs(q: String): List<DrugDomain> = drugApi
        .getDrugs(q = q)
        .mapResult { it.toDomainList() }

    /**
     * Queries detailed documentation information of a single drug medication.
     *
     * @param link Drug details key link.
     * @return Domain [DrugDomain] drug medication entity.
     */
    override suspend fun getDrugInfo(link: String): DrugDomain {
        mutex.withLock {
            infoCache.get(link)?.let { return it }
        }
        val fresh = drugApi
            .getDrugInfo(link = link)
            .mapResult { it.toDomain() }
        mutex.withLock {
            infoCache.put(link, fresh)
        }
        return fresh
    }
}