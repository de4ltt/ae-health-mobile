package feo.health.catalog.data.repository

import feo.health.catalog.clinic.api.IClinicApi
import feo.health.catalog.data.mapper.ClinicDtoToClinicDomainMapper.toDomain
import feo.health.catalog.data.mapper.ClinicDtoToClinicDomainMapper.toDomainList
import feo.health.catalog.data.mapper.DoctorDtoToDoctorDomainMapper.toDomainList
import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.domain.model.DoctorDomain
import feo.health.catalog.domain.repository.IClinicRepository
import feo.health.network.model.mapResult
import javax.inject.Inject

/**
 * Repository interface implementation managing remote clinic search listings and related staff information queries.
 *
 * @property clinicApi Remote clinic search API service client.
 */
class ClinicRepository @Inject constructor(
    private val clinicApi: IClinicApi
) : IClinicRepository {

    /**
     * Queries clinics matching text keyword query.
     *
     * @param q Text keyword.
     * @param isLocated Coordinates filter boundaries flag.
     * @return Domain list of matched [ClinicDomain] clinics.
     */
    override suspend fun getClinics(
        q: String,
        isLocated: Boolean
    ): List<ClinicDomain> = clinicApi
        .getClinics(q = q, isLocated = isLocated)
        .mapResult { it.toDomainList() }

    /**
     * Queries clinics associated with a clinic category.
     *
     * @param link Specialty link identifier.
     * @return Domain list of matched [ClinicDomain] clinics.
     */
    override suspend fun getClinicsByType(link: String): List<ClinicDomain> = clinicApi
        .getClinicsByType(link = link)
        .mapResult{ it.toDomainList() }

    /**
     * Queries details of a single clinic.
     *
     * @param link Clinic details key link.
     * @param isLocated Coordinates boundaries filter flag.
     * @return Domain [ClinicDomain] clinic details entity.
     */
    override suspend fun getClinicInfo(
        link: String,
        isLocated: Boolean
    ): ClinicDomain = clinicApi
        .getClinicInfo(link = link, isLocated = isLocated)
        .mapResult{ it.toDomain() }

    /**
     * Queries list of doctor specialists practicing at a clinic.
     *
     * @param link Clinic details key link.
     * @return Domain list of matched [DoctorDomain] doctors.
     */
    override suspend fun getClinicDoctors(link: String): List<DoctorDomain> = clinicApi
        .getClinicDoctors(link = link)
        .mapResult{ it.toDomainList() }
}