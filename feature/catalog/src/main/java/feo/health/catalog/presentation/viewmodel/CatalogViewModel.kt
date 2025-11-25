package feo.health.catalog.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import feo.health.catalog.domain.use_case.util.ICatalogUseCases
import feo.health.catalog.presentation.mapper.ClinicDomainToClinicMapper.toClinic
import feo.health.catalog.presentation.mapper.ClinicDomainToClinicMapper.toClinicList
import feo.health.catalog.presentation.mapper.CoordsDomainToCoordsMapper.toCoords
import feo.health.catalog.presentation.mapper.DoctorToDoctorDomainMapper.toDoctor
import feo.health.catalog.presentation.mapper.DoctorToDoctorDomainMapper.toDoctorList
import feo.health.catalog.presentation.mapper.PharmacyToPharmacyDomainMapper.toPharmacyList
import feo.health.catalog.presentation.mapper.ServiceToServiceDomainMapper.toServiceList
import feo.health.catalog.presentation.model.ICatalog
import feo.health.catalog.presentation.util.Mock
import feo.health.catalog.presentation.viewmodel.companion.CatalogEvent
import feo.health.catalog.presentation.viewmodel.companion.CatalogState
import feo.health.catalog.presentation.viewmodel.companion.SearchBarState
import feo.health.ui.component.HToast
import feo.health.ui.component.HToast.tryWithToast
import feo.health.ui.location.LocationService
import feo.health.ui.model.ICatalogItem
import feo.health.ui.viewmodel.HViewModel
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val clinicUseCases: ICatalogUseCases.Clinic,
    private val doctorUseCases: ICatalogUseCases.Doctor,
    private val pharmacyUseCases: ICatalogUseCases.Pharmacy,
    private val serviceUseCases: ICatalogUseCases.Service,
    private val searchUseCases: ICatalogUseCases.Search,
) : HViewModel<CatalogState, CatalogEvent>(
    initialState = CatalogState.Items.Default
) {
    override fun onEvent(event: CatalogEvent) = when (event) {
        is CatalogEvent.ItemInfoEvent.OnDetails -> onDetails(event.item)
        is CatalogEvent.ItemInfoEvent.OnSpecialists -> onSpecialists(
            type = event.type,
            link = event.link
        )

        CatalogEvent.SearchEvent.OnSearch -> onSearch()
        CatalogEvent.OnBack -> onBack()
    }

    private fun onDetails(item: ICatalogItem) = viewModelScope.tryWithToast(
        onError = { revertScreenState() }
    ) {
        updateScreenState(CatalogState.ItemDetails.Loading(item))

        when (item) {
            is ICatalogItem.ServiceItem -> onClinicsByService(item)
            is ICatalogItem.ClinicTypeItem -> onClinicsByType(item.link!!)
            is ICatalogItem.DoctorTypeItem -> onDoctorsBySpeciality(item.link!!)

            else -> {
                val result: ICatalog = when (item) {

                    is ICatalogItem.DoctorItem -> doctorUseCases.getDoctorInfoUseCase(link = item.link!!)
                        .toDoctor()

                    is ICatalogItem.ClinicItem -> {
                        var clinic = clinicUseCases.getClinicInfoUseCase(
                            link = item.link!!,
                            isLocated = false
                        ).toClinic()
                        clinic.address?.let {
                            try {
                                val coords = searchUseCases.getCoordsForAddressUseCase(it)
                                println(coords)
                                clinic = clinic.copy(coords = coords.toCoords())
                            } catch (ignored: Exception) {
                                throw ignored
                            }
                        }
                        clinic
                    }

                    else -> item.link?.let {
                        pharmacyUseCases.getPharmacyByIdUseCase(
                            it.toLong()
                        )
                    } ?: Mock.pharmacy
                } as ICatalog
                pushScreenState(CatalogState.ItemDetails.Found(result))

            }
        }

    }

    private fun onDoctorsBySpeciality(speciality: String) = viewModelScope.tryWithToast(
        onError = { revertScreenState() }
    ) {
        updateScreenState(CatalogState.Items.Loading)
        val result = doctorUseCases.getDoctorsBySpecialityUseCase(speciality).toDoctorList()
            .map { it.getCatalogItem() }
        pushScreenState(
            if (result.isEmpty()) CatalogState.Items.NothingFound else CatalogState.Items.Found(
                result
            )
        )
        SearchBarState.enableInput()
    }

    private fun onClinicsByType(type: String) = viewModelScope.tryWithToast(
        onError = { revertScreenState() }
    ) {
        updateScreenState(CatalogState.Items.Loading)
        val result =
            clinicUseCases.getClinicsByTypeUseCase(type).toClinicList().map { it.getCatalogItem() }
        pushScreenState(
            if (result.isEmpty()) CatalogState.Items.NothingFound else CatalogState.Items.Found(
                result
            )
        )
        SearchBarState.enableInput()
    }

    private fun onClinicsByService(item: ICatalogItem) = viewModelScope.tryWithToast(
        onError = { revertScreenState() }
    ) {
        updateScreenState(CatalogState.Items.Loading)
        val result = serviceUseCases.getClinicsByServiceUseCase(item.link!!).toClinicList()
            .map { it.getCatalogItem() }
        pushScreenState(
            if (result.isEmpty()) CatalogState.Items.NothingFound else CatalogState.Items.Found(
                result
            )
        )
        SearchBarState.enableInput()
    }

    private fun onSpecialists(type: ICatalogItem.Companion.CatalogItemType, link: String) =
        viewModelScope.tryWithToast(
            onError = {
                revertScreenState()
            }
        ) {
            if (type == ICatalogItem.Companion.CatalogItemType.CLINIC) {
                updateScreenState(CatalogState.ItemSpecialists.Loading)
                val result = clinicUseCases.getClinicDoctorsUseCase(link).toDoctorList()
                pushScreenState(CatalogState.ItemSpecialists.Found(result))
            } else HToast.makeError()
        }

    private fun onSearch() = viewModelScope.tryWithToast(
        successMessageRequired = true,
        onError = {
            revertScreenState()
            SearchBarState.enableInput()
        }
    ) {
        SearchBarState.disableInput()
        updateScreenState(CatalogState.Items.Loading)

        val q = SearchBarState.input.value

        val types = SearchBarState.FiltersState.getActivatedTypes()
        val result =
            if (SearchBarState.Filters.Type.entries.all { types.contains(it) } || types.isEmpty())
                searchAll(q)
            else {
                val res: MutableList<ICatalogItem> = mutableListOf()
                types.forEach {
                    Log.d("INFO_CLASS", "$it")
                    res += when (it) {
                        is SearchBarState.Filters.Type.Doctors -> searchDoctors(q)
                        is SearchBarState.Filters.Type.Clinics -> searchClinics(q)
                        is SearchBarState.Filters.Type.Pharmacies -> {
                            val location = LocationService.locationState.value
                            when (location) {
                                is LocationService.LocationState.Success -> searchPharmacies(
                                    lon = location.location.longitude,
                                    lat = location.location.longitude,
                                    radius = 500
                                )

                                else -> emptyList<ICatalogItem>()
                            }
                        }

                        is SearchBarState.Filters.Type.Services -> searchServices(q)

                        else -> emptyList<ICatalogItem>()
                    }
                }
                res
            }



        pushScreenState(
            if (result.isEmpty()) CatalogState.Items.NothingFound else CatalogState.Items.Found(
                result
            )
        )
    }

    private suspend fun searchAll(query: String): List<ICatalogItem> {
        val result = mutableListOf<ICatalogItem>()
        searchUseCases.searchUseCase.invoke(q = query, isLocated = false).let {
            result.addAll(it.doctors.toDoctorList().map { el -> el.getCatalogItem() })
            result.addAll(it.clinics.toClinicList().map { el -> el.getCatalogItem() })
            result.addAll(it.services.toServiceList().map { el -> el.getCatalogItem() })
        }
        return result
    }

    private suspend fun searchServices(query: String): List<ICatalogItem> =
        serviceUseCases.getServicesUseCase(query).toServiceList().map { it.getCatalogItem() }

    private suspend fun searchClinics(query: String): List<ICatalogItem> =
        clinicUseCases.getClinicsUseCase(q = query, isLocated = false).toClinicList()
            .map { it.getCatalogItem() }

    private suspend fun searchDoctors(query: String): List<ICatalogItem> =
        doctorUseCases.getDoctorsUseCase(q = query).toDoctorList()
            .map { it.getCatalogItem() }

    private suspend fun searchPharmacies(
        lat: Double,
        lon: Double,
        radius: Int
    ): List<ICatalogItem> =
        pharmacyUseCases.getPharmaciesUseCase(lat = lat, lon = lon, radius = radius)
            .toPharmacyList()
            .map { it.getCatalogItem() }


}