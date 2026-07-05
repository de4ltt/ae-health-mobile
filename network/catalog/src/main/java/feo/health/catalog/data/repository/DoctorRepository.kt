package feo.health.catalog.data.repository

import feo.health.catalog.data.mapper.DoctorDtoToDoctorDomainMapper.toDomain
import feo.health.catalog.data.mapper.DoctorDtoToDoctorDomainMapper.toDomainList
import feo.health.catalog.doctor.api.IDoctorApi
import feo.health.catalog.domain.model.DoctorDomain
import feo.health.catalog.domain.repository.IDoctorRepository
import feo.health.network.model.mapResult
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

/**
 * Repository interface implementation managing remote doctor specialty searches and details retrieval.
 *
 * @property doctorApi Remote doctor search API service client.
 */
class DoctorRepository @Inject constructor(
    private val doctorApi: IDoctorApi
) : IDoctorRepository {

    private val infoCache = RepositoryCache<String, DoctorDomain>(50)
    private val mutex = Mutex()

    /**
     * Queries doctors matching text keyword query.
     *
     * @param q Text keyword.
     * @return Domain list of matched [DoctorDomain] doctors.
     */
    override suspend fun getDoctors(q: String): List<DoctorDomain> = doctorApi
        .getDoctors(q = q)
        .mapResult { it.toDomainList() }

    /**
     * Queries detailed profile information of a single doctor.
     *
     * @param link Doctor details key link.
     * @return Domain [DoctorDomain] doctor profile entity.
     */
    override suspend fun getDoctorInfo(link: String): DoctorDomain {
        mutex.withLock {
            infoCache.get(link)?.let { return it }
        }
        val fresh = doctorApi
            .getDoctorInfo(link = link)
            .mapResult { it.toDomain() }
        mutex.withLock {
            infoCache.put(link, fresh)
        }
        return fresh
    }

    /**
     * Queries doctors belonging to a specific specialty.
     *
     * @param speciality Specialty link identifier.
     * @return Domain list of matched [DoctorDomain] doctors.
     */
    override suspend fun getDoctorsBySpeciality(speciality: String): List<DoctorDomain> = doctorApi
        .getDoctorsBySpeciality(speciality = speciality)
        .mapResult { it.toDomainList() }
}