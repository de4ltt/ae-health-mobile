package feo.health.catalog.presentation.mapper

import feo.health.catalog.domain.model.DoctorDomain
import feo.health.catalog.presentation.mapper.DoctorSpecialityToDoctorSpecialityDomainMapper.toDoctorSpecialityDomainList
import feo.health.catalog.presentation.mapper.DoctorSpecialityToDoctorSpecialityDomainMapper.toDoctorSpecialityList
import feo.health.catalog.presentation.mapper.ReviewToReviewDomainMapper.toReviewDomainList
import feo.health.catalog.presentation.mapper.ReviewToReviewDomainMapper.toReviewList
import feo.health.catalog.presentation.model.ICatalog
import feo.health.ui.model.ICatalogItem
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object DoctorMapper: IMapper<ICatalog.Doctor, DoctorDomain> {
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