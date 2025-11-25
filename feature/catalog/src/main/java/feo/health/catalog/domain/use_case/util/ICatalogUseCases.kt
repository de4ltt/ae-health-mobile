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

interface ICatalogUseCases {
    interface Clinic {
        val getClinicDoctorsUseCase: GetClinicDoctorsUseCase
        val getClinicInfoUseCase: GetClinicInfoUseCase
        val getClinicsByTypeUseCase: GetClinicsByTypeUseCase
        val getClinicsUseCase: GetClinicsUseCase
    }
    interface Pharmacy {
        val getPharmaciesUseCase: GetPharmaciesUseCase
        val getPharmacyByIdUseCase: GetPharmacyByIdUseCase
        val visitPharmacyUseCase: VisitPharmacyUseCase
    }
    interface Doctor {
        val getDoctorInfoUseCase: GetDoctorInfoUseCase
        val getDoctorsBySpecialityUseCase: GetDoctorsBySpecialityUseCase
        val getDoctorsUseCase: GetDoctorsUseCase
    }
    interface Service {
        val getClinicsByServiceUseCase: GetClinicsByServiceUseCase
        val getServicesUseCase: GetServicesUseCase
    }
    interface Search {
        val searchUseCase: SearchUseCase
        val getCoordsForAddressUseCase: GetCoordsForAddressUseCase
    }
}