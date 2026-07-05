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

/**
 * Wrapper object holding groups of health catalog use cases.
 */
object CatalogUseCases {

    /**
     * Use cases related to clinic operations.
     *
     * @property getClinicDoctorsUseCase Use case to get doctors of a clinic.
     * @property getClinicInfoUseCase Use case to get detailed clinic information.
     * @property getClinicsByTypeUseCase Use case to get clinics by type.
     * @property getClinicsUseCase Use case to get/search clinics.
     */
    class Clinic @Inject constructor(
        override val getClinicDoctorsUseCase: GetClinicDoctorsUseCase,
        override val getClinicInfoUseCase: GetClinicInfoUseCase,
        override val getClinicsByTypeUseCase: GetClinicsByTypeUseCase,
        override val getClinicsUseCase: GetClinicsUseCase
    ): ICatalogUseCases.Clinic

    /**
     * Use cases related to doctor operations.
     *
     * @property getDoctorInfoUseCase Use case to get detailed doctor information.
     * @property getDoctorsBySpecialityUseCase Use case to get doctors by speciality.
     * @property getDoctorsUseCase Use case to get/search doctors.
     */
    class Doctor @Inject constructor(
        override val getDoctorInfoUseCase: GetDoctorInfoUseCase,
        override val getDoctorsBySpecialityUseCase: GetDoctorsBySpecialityUseCase,
        override val getDoctorsUseCase: GetDoctorsUseCase
    ): ICatalogUseCases.Doctor

    /**
     * Use cases related to pharmacy operations.
     *
     * @property getPharmaciesUseCase Use case to get nearby pharmacies.
     * @property getPharmacyByIdUseCase Use case to get detailed pharmacy information by ID.
     * @property visitPharmacyUseCase Use case to record a pharmacy visit.
     */
    class Pharmacy @Inject constructor(
        override val getPharmaciesUseCase: GetPharmaciesUseCase,
        override val getPharmacyByIdUseCase: GetPharmacyByIdUseCase,
        override val visitPharmacyUseCase: VisitPharmacyUseCase
    ): ICatalogUseCases.Pharmacy

    /**
     * Use cases related to service operations.
     *
     * @property getClinicsByServiceUseCase Use case to get clinics that offer a specific service.
     * @property getServicesUseCase Use case to get/search services.
     */
    class Service @Inject constructor(
        override val getClinicsByServiceUseCase: GetClinicsByServiceUseCase,
        override val getServicesUseCase: GetServicesUseCase
    ): ICatalogUseCases.Service

    /**
     * Use cases related to catalog search and geocoding operations.
     *
     * @property searchUseCase Use case to perform unified search.
     * @property getCoordsForAddressUseCase Use case to geocode an address into coordinates.
     */
    class Search @Inject constructor(
        override val searchUseCase: SearchUseCase,
        override val getCoordsForAddressUseCase: GetCoordsForAddressUseCase
    ): ICatalogUseCases.Search

}
