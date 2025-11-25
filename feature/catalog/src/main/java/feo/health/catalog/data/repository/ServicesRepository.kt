package feo.health.catalog.data.repository

import feo.health.catalog.data.mapper.ClinicDtoToClinicDomainMapper.toClinicDomainList
import feo.health.catalog.data.mapper.ServiceDtoToServiceDomainMapper.toServiceDomainList
import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.domain.model.ServiceDomain
import feo.health.catalog.domain.repository.IServicesRepository
import feo.health.catalog.services.api.IServicesApi
import feo.health.network.model.mapResult
import javax.inject.Inject

class ServicesRepository @Inject constructor(
    private val servicesApi: IServicesApi
) : IServicesRepository {

    override suspend fun getServices(q: String): List<ServiceDomain> = servicesApi
        .getServices(q = q)
        .mapResult { it.toServiceDomainList() }

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