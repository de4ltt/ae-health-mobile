package feo.health.catalog.data.mapper

import feo.health.catalog.data.mapper.ClinicDtoToClinicDomainMapper.toClinicDomainList
import feo.health.catalog.data.mapper.ClinicDtoToClinicDomainMapper.toClinicDtoList
import feo.health.catalog.data.mapper.DoctorDtoToDoctorDomainMapper.toDoctorDomainList
import feo.health.catalog.data.mapper.DoctorDtoToDoctorDomainMapper.toDoctorDtoList
import feo.health.catalog.data.mapper.ServiceDtoToServiceDomainMapper.toServiceDomainList
import feo.health.catalog.data.mapper.ServiceDtoToServiceDomainMapper.toServiceDtoList
import feo.health.catalog.domain.model.SearchDomain
import feo.health.catalog.search.dto.SearchDto
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object SearchMapper : IMapper<SearchDto, SearchDomain> {
    override fun SearchDto.toSecond(): SearchDomain =
        SearchDomain(
            doctors = doctors.toDoctorDomainList(),
            clinics = clinics.toClinicDomainList(),
            services = services.toServiceDomainList()
        )

    override fun SearchDomain.toFirst(): SearchDto =
        SearchDto(
            doctors = doctors.toDoctorDtoList(),
            clinics = clinics.toClinicDtoList(),
            services = services.toServiceDtoList()
        )

}