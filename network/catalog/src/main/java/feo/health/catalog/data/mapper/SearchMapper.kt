package feo.health.catalog.data.mapper

import feo.health.catalog.domain.model.SearchDomain
import feo.health.catalog.search.dto.SearchDto
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper
import feo.health.catalog.data.mapper.ClinicDtoToClinicDomainMapper.toDomainList as toClinicDomainList
import feo.health.catalog.data.mapper.ClinicDtoToClinicDomainMapper.toDtoList as toClinicDtoList
import feo.health.catalog.data.mapper.DoctorDtoToDoctorDomainMapper.toDomainList as toDoctorDomainList
import feo.health.catalog.data.mapper.DoctorDtoToDoctorDomainMapper.toDtoList as toDoctorDtoList
import feo.health.catalog.data.mapper.ServiceDtoToServiceDomainMapper.toDomainList as toServiceDomainList
import feo.health.catalog.data.mapper.ServiceDtoToServiceDomainMapper.toDtoList as toServiceDtoList

/**
 * Data mapping resolver between serialization [SearchDto] and domain [SearchDomain] models.
 */
@Mapper
private object SearchMapper : IMapper<SearchDto, SearchDomain> {
    /**
     * Converts a [SearchDto] serial model to its corresponding domain [SearchDomain] entity.
     *
     * @return Resolved [SearchDomain].
     */
    override fun SearchDto.toSecond(): SearchDomain =
        SearchDomain(
            doctors = doctors.toDoctorDomainList(),
            clinics = clinics.toClinicDomainList(),
            services = services.toServiceDomainList()
        )

    /**
     * Converts a [SearchDomain] domain entity to its corresponding serial [SearchDto] model.
     *
     * @return Resolved [SearchDto].
     */
    override fun SearchDomain.toFirst(): SearchDto =
        SearchDto(
            doctors = doctors.toDoctorDtoList(),
            clinics = clinics.toClinicDtoList(),
            services = services.toServiceDtoList()
        )
}