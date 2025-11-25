package feo.health.catalog.domain.use_case.util

import feo.health.catalog.domain.use_case.clinic.GetClinicDoctorsUseCase
import feo.health.catalog.domain.use_case.clinic.GetClinicInfoUseCase
import feo.health.catalog.domain.use_case.clinic.GetClinicsByTypeUseCase
import feo.health.catalog.domain.use_case.clinic.GetClinicsUseCase
import feo.health.catalog.domain.use_case.doctor.GetDoctorInfoUseCase
import feo.health.catalog.domain.use_case.doctor.GetDoctorsBySpecialityUseCase
import feo.health.catalog.domain.use_case.doctor.GetDoctorsUseCase
import feo.health.catalog.domain.use_case.pharmacy.GetPharmaciesUseCase
import feo.health.catalog.domain.use_case.pharmacy.GetPharmacyByIdUseCase
import feo.health.catalog.domain.use_case.pharmacy.VisitPharmacyUseCase
import feo.health.catalog.domain.use_case.search.GetCoordsForAddressUseCase
import feo.health.catalog.domain.use_case.search.SearchUseCase
import feo.health.catalog.domain.use_case.service.GetClinicsByServiceUseCase
import feo.health.catalog.domain.use_case.service.GetServicesUseCase
import javax.inject.Inject

object CatalogUseCases {

    class Clinic @Inject constructor(
        override val getClinicDoctorsUseCase: GetClinicDoctorsUseCase,
        override val getClinicInfoUseCase: GetClinicInfoUseCase,
        override val getClinicsByTypeUseCase: GetClinicsByTypeUseCase,
        override val getClinicsUseCase: GetClinicsUseCase
    ): ICatalogUseCases.Clinic

    class Doctor @Inject constructor(
        override val getDoctorInfoUseCase: GetDoctorInfoUseCase,
        override val getDoctorsBySpecialityUseCase: GetDoctorsBySpecialityUseCase,
        override val getDoctorsUseCase: GetDoctorsUseCase
    ): ICatalogUseCases.Doctor

    class Pharmacy @Inject constructor(
        override val getPharmaciesUseCase: GetPharmaciesUseCase,
        override val getPharmacyByIdUseCase: GetPharmacyByIdUseCase,
        override val visitPharmacyUseCase: VisitPharmacyUseCase
    ): ICatalogUseCases.Pharmacy

    class Service @Inject constructor(
        override val getClinicsByServiceUseCase: GetClinicsByServiceUseCase,
        override val getServicesUseCase: GetServicesUseCase
    ): ICatalogUseCases.Service

    class Search @Inject constructor(
        override val searchUseCase: SearchUseCase,
        override val getCoordsForAddressUseCase: GetCoordsForAddressUseCase
    ): ICatalogUseCases.Search

}
