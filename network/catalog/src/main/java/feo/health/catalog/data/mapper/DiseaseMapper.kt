package feo.health.catalog.data.mapper

import feo.health.catalog.disease.dto.DiseaseDto
import feo.health.catalog.domain.model.DiseaseDomain
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between serialization [DiseaseDto] and domain [DiseaseDomain] models.
 */
@Mapper
private object DiseaseMapper : IMapper<DiseaseDto, DiseaseDomain> {
    /**
     * Converts a [DiseaseDto] serial model to its corresponding domain [DiseaseDomain] entity.
     *
     * @return Resolved [DiseaseDomain].
     */
    override fun DiseaseDto.toSecond(): DiseaseDomain =
        DiseaseDomain(
            name = name,
            link = link
        )

    /**
     * Converts a [DiseaseDomain] domain entity to its corresponding serial [DiseaseDto] model.
     *
     * @return Resolved [DiseaseDto].
     */
    override fun DiseaseDomain.toFirst(): DiseaseDto =
        DiseaseDto(
            name = name,
            link = link
        )
}