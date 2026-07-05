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

/**
 * ViewModel that handles business logic for displaying, searching, and managing catalog items
 * such as doctors, clinics, services, and pharmacies.
 *
 * Extends [HViewModel] with [CatalogState] and [CatalogEvent].
 *
 * @property clinicUseCases Clinic business logic use cases.
 * @property doctorUseCases Doctor business logic use cases.
 * @property pharmacyUseCases Pharmacy business logic use cases.
 * @property serviceUseCases Service business logic use cases.
 * @property searchUseCases Search business logic use cases.
 */
class CatalogViewModel @Inject constructor(
    private val clinicUseCases: ICatalogUseCases.Clinic,
    private val doctorUseCases: ICatalogUseCases.Doctor,
    private val pharmacyUseCases: ICatalogUseCases.Pharmacy,
    private val serviceUseCases: ICatalogUseCases.Service,
    private val searchUseCases: ICatalogUseCases.Search,
) : HViewModel<CatalogState, CatalogEvent>(
    initialState = CatalogState.Items.Default
) {
    /**
     * Processes incoming UI events and performs transitions.
     *
     * @param event The catalog event to handle.
     */
    override fun onEvent(event: CatalogEvent) = when (event) {
        is CatalogEvent.ItemInfoEvent.OnDetails -> onDetails(event.item)
        is CatalogEvent.ItemInfoEvent.OnSpecialists -> onSpecialists(
            type = event.type,
            link = event.link
        )

        CatalogEvent.SearchEvent.OnSearch -> onSearch()
        CatalogEvent.OnBack -> onBack()
    }

    /**
     * Loads detailed information for the specified catalog item.
     *
     * @param item The selected catalog item to inspect.
     */
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

    /**
     * Loads and displays doctors matching a given specialty.
     *
     * @param speciality The specialty identifier.
     */
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

    /**
     * Loads and displays clinics matching a given clinic type.
     *
     * @param type The clinic type identifier.
     */
    private fun onClinicsByType(type: String) = viewModelScope.tryWithToast(
        onError = {
            pushScreenState(CatalogState.Items.Error("Не удалось загрузить данные по типу"))
            SearchBarState.enableInput()
        }
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

    /**
     * Loads and displays clinics offering a given service.
     *
     * @param item The selected service item.
     */
    private fun onClinicsByService(item: ICatalogItem) = viewModelScope.tryWithToast(
        onError = {
            pushScreenState(CatalogState.Items.Error("Не удалось загрузить данные по услуге"))
            SearchBarState.enableInput()
        }
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

    /**
     * Loads the specialists list for a given clinic organization.
     *
     * @param type The catalog item type of the organization.
     * @param link The link/ID key of the organization.
     */
    private fun onSpecialists(type: ICatalogItem.Companion.CatalogItemType, link: String) =
        viewModelScope.tryWithToast(
            onError = {
                pushScreenState(CatalogState.Items.Error("Не удалось загрузить список специалистов"))
            }
        ) {
            if (type == ICatalogItem.Companion.CatalogItemType.CLINIC) {
                updateScreenState(CatalogState.ItemSpecialists.Loading)
                val result = clinicUseCases.getClinicDoctorsUseCase(link).toDoctorList()
                pushScreenState(CatalogState.ItemSpecialists.Found(result))
            } else HToast.makeError()
        }

    /**
     * Executes search based on the query text input and active search filters.
     */
    private fun onSearch() = viewModelScope.tryWithToast(
        onError = {
            pushScreenState(CatalogState.Items.Error("Произошла ошибка при поиске"))
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
                                is LocationService.LocationState.Success -> {
                                    val selectedRadiusFilter = SearchBarState.FiltersState.selectedRadius.value.firstOrNull()
                                    val radiusInMeters = when (selectedRadiusFilter) {
                                        is SearchBarState.Filters.Radius.FiveHundredMeters -> 500
                                        is SearchBarState.Filters.Radius.OneKilometer -> 1000
                                        is SearchBarState.Filters.Radius.TwoKilometers -> 2000
                                        is SearchBarState.Filters.Radius.Any -> 50000
                                        else -> 500
                                    }
                                    searchPharmacies(
                                        lon = location.location.longitude,
                                        lat = location.location.latitude,
                                        radius = radiusInMeters
                                    )
                                }

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
        SearchBarState.enableInput()
    }

    /**
     * Helper to search all doctors, clinics, and services matching a query.
     *
     * @param query The query string.
     * @return List of matching catalog items.
     */
    private suspend fun searchAll(query: String): List<ICatalogItem> {
        val result = mutableListOf<ICatalogItem>()
        searchUseCases.searchUseCase.invoke(q = query, isLocated = false).let {
            result.addAll(it.doctors.toDoctorList().map { el -> el.getCatalogItem() })
            result.addAll(it.clinics.toClinicList().map { el -> el.getCatalogItem() })
            result.addAll(it.services.toServiceList().map { el -> el.getCatalogItem() })
        }
        return result
    }

    /**
     * Helper to search services.
     *
     * @param query The query string.
     * @return List of matching services.
     */
    private suspend fun searchServices(query: String): List<ICatalogItem> =
        serviceUseCases.getServicesUseCase(query).toServiceList().map { it.getCatalogItem() }

    /**
     * Helper to search clinics.
     *
     * @param query The query string.
     * @return List of matching clinics.
     */
    private suspend fun searchClinics(query: String): List<ICatalogItem> =
        clinicUseCases.getClinicsUseCase(q = query, isLocated = false).toClinicList()
            .map { it.getCatalogItem() }

    /**
     * Helper to search doctors.
     *
     * @param query The query string.
     * @return List of matching doctors.
     */
    private suspend fun searchDoctors(query: String): List<ICatalogItem> =
        doctorUseCases.getDoctorsUseCase(q = query).toDoctorList()
            .map { it.getCatalogItem() }

    /**
     * Helper to search pharmacies within a location radius.
     *
     * @param lat Latitude coordinate.
     * @param lon Longitude coordinate.
     * @param radius Search radius in meters.
     * @return List of matching pharmacies.
     */
    private suspend fun searchPharmacies(
        lat: Double,
        lon: Double,
        radius: Int
    ): List<ICatalogItem> =
        pharmacyUseCases.getPharmaciesUseCase(lat = lat, lon = lon, radius = radius)
            .toPharmacyList()
            .map { it.getCatalogItem() }


}