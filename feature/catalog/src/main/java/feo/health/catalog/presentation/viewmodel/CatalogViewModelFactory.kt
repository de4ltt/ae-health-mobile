package feo.health.catalog.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import feo.health.catalog.domain.use_case.util.ICatalogUseCases
import javax.inject.Inject

class CatalogViewModelFactory @Inject constructor(
    private val clinicUseCases: ICatalogUseCases.Clinic,
    private val doctorUseCases: ICatalogUseCases.Doctor,
    private val serviceUseCases: ICatalogUseCases.Service,
    private val searchUseCases: ICatalogUseCases.Search,
    private val pharmacyUseCases: ICatalogUseCases.Pharmacy,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatalogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatalogViewModel(
                clinicUseCases = clinicUseCases,
                doctorUseCases = doctorUseCases,
                serviceUseCases = serviceUseCases,
                searchUseCases = searchUseCases,
                pharmacyUseCases = pharmacyUseCases,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}