package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.DoctorDomain
import feo.health.catalog.presentation.mapper.DoctorSpecialityToDoctorSpecialityDomainMapper.toDomainList as toDoctorSpecialityDomainList
import feo.health.catalog.presentation.mapper.DoctorSpecialityToDoctorSpecialityDomainMapper.toDoctorSpecialityList
import feo.health.catalog.presentation.mapper.ReviewToReviewDomainMapper.toDomainList as toReviewDomainList
import feo.health.catalog.presentation.mapper.ReviewToReviewDomainMapper.toReviewList
import feo.health.catalog.presentation.model.ICatalog
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Mapper object for translating between [ICatalog.Doctor] and [DoctorDomain].
 *
 * This mapper uses the @Mapper annotation and implements [IMapper].
 */
@Mapper
private object DoctorMapper: IMapper<ICatalog.Doctor, DoctorDomain> {
    /**
     * Converts an [ICatalog.Doctor] instance to [DoctorDomain].
     *
     * @return The converted [DoctorDomain] instance.
     */
    override fun ICatalog.Doctor.toSecond(): DoctorDomain =
        DoctorDomain(
            name = name,
            link = link,
            specialities = specialities?.toDoctorSpecialityDomainList(),
            experience = experience,
            imageUri = imageUri,
            rating = rating,
            itemType = itemType,
            reviews = reviews?.toReviewDomainList()
        )

    /**
     * Converts a [DoctorDomain] instance back to [ICatalog.Doctor].
     *
     * @return The converted [ICatalog.Doctor] instance.
     */
    override fun DoctorDomain.toFirst(): ICatalog.Doctor =
        ICatalog.Doctor(
            name = name,
            link = link,
            specialities = specialities?.toDoctorSpecialityList(),
            experience = experience,
            imageUri = imageUri,
            rating = rating,
            itemType = itemType,
            reviews = reviews?.toReviewList()
        )

}