package feo.health.catalog.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import feo.health.catalog.domain.use_case.util.ICatalogUseCases
import javax.inject.Inject

/**
 * Factory for creating [CatalogViewModel] instances injected with the catalog use cases.
 *
 * Implements [ViewModelProvider.Factory].
 *
 * @property clinicUseCases Clinic use case dependencies.
 * @property doctorUseCases Doctor/specialist use case dependencies.
 * @property serviceUseCases Medical service use case dependencies.
 * @property searchUseCases Search functionality use case dependencies.
 * @property pharmacyUseCases Pharmacy use case dependencies.
 */
class CatalogViewModelFactory @Inject constructor(
    private val clinicUseCases: ICatalogUseCases.Clinic,
    private val doctorUseCases: ICatalogUseCases.Doctor,
    private val serviceUseCases: ICatalogUseCases.Service,
    private val searchUseCases: ICatalogUseCases.Search,
    private val pharmacyUseCases: ICatalogUseCases.Pharmacy,
) : ViewModelProvider.Factory {

    /**
     * Creates a new instance of the given [ViewModel] class.
     *
     * @param modelClass The class of the ViewModel to create.
     * @return A newly created ViewModel instance cast to [T].
     * @throws IllegalArgumentException if the [modelClass] is not assignable from [CatalogViewModel].
     */
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