package feo.health.catalog.data.mapper

import feo.health.catalog.disease.dto.DiseaseDto
import feo.health.catalog.domain.model.DiseaseDomain
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object DiseaseMapper : IMapper<DiseaseDto, DiseaseDomain> {
    override fun DiseaseDto.toSecond(): DiseaseDomain =
        DiseaseDomain(
            name = name,
            link = link
        )

    override fun DiseaseDomain.toFirst(): DiseaseDto =
        DiseaseDto(
            name = name,
            link = link
        )
}