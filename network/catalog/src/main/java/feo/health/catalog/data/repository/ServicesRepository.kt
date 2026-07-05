package feo.health.catalog.data.repository

import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.domain.model.ServiceDomain
import feo.health.catalog.domain.repository.IServicesRepository
import feo.health.catalog.services.api.IServicesApi
import feo.health.network.model.mapResult
import javax.inject.Inject
import feo.health.catalog.data.mapper.ClinicDtoToClinicDomainMapper.toDomainList as toClinicDomainList
import feo.health.catalog.data.mapper.ServiceDtoToServiceDomainMapper.toDomainList as toServiceDomainList

/**
 * Repository interface implementation managing remote medical services queries and related clinics retrieval.
 *
 * @property servicesApi Remote services search API service client.
 */
class ServicesRepository @Inject constructor(
    private val servicesApi: IServicesApi
) : IServicesRepository {

    /**
     * Queries medical services matching text keyword query.
     *
     * @param q Text keyword.
     * @return Domain list of matched [ServiceDomain] services.
     */
    override suspend fun getServices(q: String): List<ServiceDomain> = servicesApi
        .getServices(q = q)
        .mapResult { it.toServiceDomainList() }

    /**
     * Queries clinics providing a specific medical service. Sanitizes clinical URL price parameters.
     *
     * @param link Relative service navigation key link.
     * @return Domain list of matched [ClinicDomain] clinics.
     */
    override suspend fun getClinicsByService(link: String): List<ClinicDomain> = servicesApi
        .getClinicsByService(link = link)
        .mapResult {
            it.map { element ->
                element.copy(
                    link = element.link.replace(
                        Regex("price#[a-zA-Z]+"),
                        ""
                    )
                )
            }.toClinicDomainList()
        }
}