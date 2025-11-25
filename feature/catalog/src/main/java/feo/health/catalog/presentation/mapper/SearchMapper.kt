package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.SearchDomain
import feo.health.catalog.presentation.mapper.ClinicDomainToClinicMapper.toClinicDomainList
import feo.health.catalog.presentation.mapper.ClinicDomainToClinicMapper.toClinicList
import feo.health.catalog.presentation.mapper.DoctorToDoctorDomainMapper.toDoctorDomainList
import feo.health.catalog.presentation.mapper.DoctorToDoctorDomainMapper.toDoctorList
import feo.health.catalog.presentation.mapper.ServiceToServiceDomainMapper.toServiceDomainList
import feo.health.catalog.presentation.mapper.ServiceToServiceDomainMapper.toServiceList
import feo.health.catalog.presentation.model.Search
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object SearchMapper : IMapper<Search, SearchDomain> {
    override fun Search.toSecond(): SearchDomain =
        SearchDomain(
            doctors = doctors.toDoctorDomainList(),
            clinics = clinics.toClinicDomainList(),
            services = services.toServiceDomainList()
        )

    override fun SearchDomain.toFirst(): Search =
        Search(
            doctors = doctors.toDoctorList(),
            clinics = clinics.toClinicList(),
            services = services.toServiceList()
        )

}