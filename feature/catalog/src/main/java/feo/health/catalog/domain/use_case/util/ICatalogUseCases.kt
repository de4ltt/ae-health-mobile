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

/**
 * Interface grouping categories of use cases within the health catalog domain.
 */
interface ICatalogUseCases {

    /**
     * Interface defining clinic-related use case properties.
     */
    interface Clinic {
        /**
         * Use case to retrieve doctors associated with a clinic.
         */
        val getClinicDoctorsUseCase: GetClinicDoctorsUseCase

        /**
         * Use case to retrieve detailed information about a clinic.
         */
        val getClinicInfoUseCase: GetClinicInfoUseCase

        /**
         * Use case to retrieve clinics filtered by type/category.
         */
        val getClinicsByTypeUseCase: GetClinicsByTypeUseCase

        /**
         * Use case to search or retrieve clinics.
         */
        val getClinicsUseCase: GetClinicsUseCase
    }

    /**
     * Interface defining pharmacy-related use case properties.
     */
    interface Pharmacy {
        /**
         * Use case to retrieve pharmacies within an area.
         */
        val getPharmaciesUseCase: GetPharmaciesUseCase

        /**
         * Use case to retrieve detailed pharmacy information by ID.
         */
        val getPharmacyByIdUseCase: GetPharmacyByIdUseCase

        /**
         * Use case to record a pharmacy visit.
         */
        val visitPharmacyUseCase: VisitPharmacyUseCase
    }

    /**
     * Interface defining doctor-related use case properties.
     */
    interface Doctor {
        /**
         * Use case to retrieve detailed information about a doctor.
         */
        val getDoctorInfoUseCase: GetDoctorInfoUseCase

        /**
         * Use case to retrieve doctors filtered by speciality.
         */
        val getDoctorsBySpecialityUseCase: GetDoctorsBySpecialityUseCase

        /**
         * Use case to search or retrieve doctors.
         */
        val getDoctorsUseCase: GetDoctorsUseCase
    }

    /**
     * Interface defining service-related use case properties.
     */
    interface Service {
        /**
         * Use case to retrieve clinics that offer a specific service.
         */
        val getClinicsByServiceUseCase: GetClinicsByServiceUseCase

        /**
         * Use case to search or retrieve services.
         */
        val getServicesUseCase: GetServicesUseCase
    }

    /**
     * Interface defining search and geocoding-related use case properties.
     */
    interface Search {
        /**
         * Use case to perform a unified catalog search.
         */
        val searchUseCase: SearchUseCase

        /**
         * Use case to geocode an address into coordinates.
         */
        val getCoordsForAddressUseCase: GetCoordsForAddressUseCase
    }
}