package feo.health.catalog.di

import dagger.BindsInstance
import dagger.Component
import feo.health.catalog.clinic.api.IClinicApi
import feo.health.catalog.di.module.ClinicModule
import feo.health.catalog.di.module.DiseaseModule
import feo.health.catalog.di.module.DoctorModule
import feo.health.catalog.di.module.DrugModule
import feo.health.catalog.di.module.PharmacyModule
import feo.health.catalog.di.module.SearchModule
import feo.health.catalog.di.module.ServicesModule
import feo.health.catalog.disease.api.IDiseaseApi
import feo.health.catalog.doctor.api.IDoctorApi
import feo.health.catalog.drug.api.IDrugApi
import feo.health.catalog.pharmacy.api.IPharmacyApi
import feo.health.catalog.search.api.ISearchApi
import feo.health.catalog.services.api.IServicesApi
import feo.health.network.di.component.NetworkComponent
import feo.health.secrets.di.CoreSecretsComponent

@NetworkCatalogScope
@Component(
    modules = [
        ClinicModule::class, DiseaseModule::class, DoctorModule::class, DrugModule::class,
        PharmacyModule::class, SearchModule::class, ServicesModule::class
    ],
    dependencies = [NetworkComponent::class, CoreSecretsComponent::class]
)
interface NetworkCatalogComponent {

    fun clinicApi(): IClinicApi

    fun diseaseApi(): IDiseaseApi

    fun doctorApi(): IDoctorApi

    fun drugApi(): IDrugApi

    fun pharmacyApi(): IPharmacyApi

    fun searchApi(): ISearchApi

    fun servicesApi(): IServicesApi

    @Component.Builder
    interface Builder {

        fun bindCoreSecretsComponent(coreSecretsComponent: CoreSecretsComponent): Builder

        fun bindNetworkComponent(networkComponent: NetworkComponent): Builder

        fun build(): NetworkCatalogComponent
    }
}