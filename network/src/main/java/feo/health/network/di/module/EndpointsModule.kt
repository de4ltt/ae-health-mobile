package feo.health.network.di.module

import dagger.Binds
import dagger.Module
import feo.health.network.di.NetworkModuleScope
import feo.health.network.endpoints.IApiEndpoints
import feo.health.network.endpoints.impl.AIEndpoints
import feo.health.network.endpoints.impl.AuthEndpoints
import feo.health.network.endpoints.impl.SearchEndpoints
import feo.health.network.endpoints.impl.ClinicEndpoints
import feo.health.network.endpoints.impl.DiseaseEndpoints
import feo.health.network.endpoints.impl.DoctorEndpoints
import feo.health.network.endpoints.impl.DrugEndpoints
import feo.health.network.endpoints.impl.PharmacyEndpoints
import feo.health.network.endpoints.impl.ServicesEndpoints
import feo.health.network.endpoints.impl.UserEndpoints

@Module
internal abstract class EndpointsModule {

    @NetworkModuleScope
    @Binds
    abstract fun bindAIEndpoints(aiEndpoints: AIEndpoints): IApiEndpoints.AI

    @NetworkModuleScope
    @Binds
    abstract fun bindAuthEndpoints(authEndpoints: AuthEndpoints): IApiEndpoints.Auth

    @NetworkModuleScope
    @Binds
    abstract fun bindSearchEndpoints(searchEndpoints: SearchEndpoints): IApiEndpoints.Catalog.Search

    @NetworkModuleScope
    @Binds
    abstract fun bindClinicEndpoints(clinicEndpoints: ClinicEndpoints): IApiEndpoints.Catalog.Clinic

    @NetworkModuleScope
    @Binds
    abstract fun bindDiseaseEndpoints(diseaseEndpoints: DiseaseEndpoints): IApiEndpoints.Catalog.Disease

    @NetworkModuleScope
    @Binds
    abstract fun bindDoctorEndpoints(doctorEndpoints: DoctorEndpoints): IApiEndpoints.Catalog.Doctor

    @NetworkModuleScope
    @Binds
    abstract fun bindDrugEndpoints(drugEndpoints: DrugEndpoints): IApiEndpoints.Catalog.Drug

    @NetworkModuleScope
    @Binds
    abstract fun bindPharmacyEndpoints(pharmacyEndpoints: PharmacyEndpoints): IApiEndpoints.Catalog.Pharmacy

    @NetworkModuleScope
    @Binds
    abstract fun bindServicesEndpoints(servicesEndpoints: ServicesEndpoints): IApiEndpoints.Catalog.Services

    @NetworkModuleScope
    @Binds
    abstract fun bindUserEndpoints(userEndpoints: UserEndpoints): IApiEndpoints.User
}