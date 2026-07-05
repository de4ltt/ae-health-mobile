package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.SearchDomain
import feo.health.catalog.presentation.mapper.ClinicDomainToClinicMapper.toDomainList as toClinicDomainList
import feo.health.catalog.presentation.mapper.ClinicDomainToClinicMapper.toClinicList
import feo.health.catalog.presentation.mapper.DoctorToDoctorDomainMapper.toDomainList as toDoctorDomainList
import feo.health.catalog.presentation.mapper.DoctorToDoctorDomainMapper.toDoctorList
import feo.health.catalog.presentation.mapper.ServiceToServiceDomainMapper.toDomainList as toServiceDomainList
import feo.health.catalog.presentation.mapper.ServiceToServiceDomainMapper.toServiceList
import feo.health.catalog.presentation.model.Search
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Mapper object for translating between [Search] and [SearchDomain].
 *
 * This mapper uses the @Mapper annotation and implements [IMapper].
 */
@Mapper
private object SearchMapper : IMapper<Search, SearchDomain> {
    /**
     * Converts a [Search] instance to [SearchDomain].
     *
     * @return The converted [SearchDomain] instance.
     */
    override fun Search.toSecond(): SearchDomain =
        SearchDomain(
            doctors = doctors.toDoctorDomainList(),
            clinics = clinics.toClinicDomainList(),
            services = services.toServiceDomainList()
        )

    /**
     * Converts a [SearchDomain] instance back to [Search].
     *
     * @return The converted [Search] instance.
     */
    override fun SearchDomain.toFirst(): Search =
        Search(
            doctors = doctors.toDoctorList(),
            clinics = clinics.toClinicList(),
            services = services.toServiceList()
        )

}