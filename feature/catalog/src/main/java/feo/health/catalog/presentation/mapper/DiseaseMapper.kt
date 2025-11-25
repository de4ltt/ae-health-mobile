package feo.health.catalog.presentation.mapper

import feo.health.catalog.disease.dto.DiseaseDto
import feo.health.catalog.domain.model.DiseaseDomain
import feo.health.catalog.presentation.model.Disease
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object DiseaseMapper : IMapper<Disease, DiseaseDomain> {
    override fun Disease.toSecond(): DiseaseDomain =
        DiseaseDomain(
            name = name,
            link = link
        )

    override fun DiseaseDomain.toFirst(): Disease =
        Disease(
            name = name,
            link = link
        )
}