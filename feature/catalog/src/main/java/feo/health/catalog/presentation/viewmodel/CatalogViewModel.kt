package feo.health.catalog.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feo.health.catalog.domain.use_case.search.SearchUseCase
import feo.health.catalog.presentation.model.util.CatalogItemType
import feo.health.catalog.presentation.model.util.Doctor
import feo.health.catalog.presentation.state.SearchBarState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    val searchBarState = SearchBarState()

    private val _items = MutableStateFlow<List<Doctor>>(emptyList())
    val items = _items.asStateFlow()

    fun onSearch() = viewModelScope.launch(Dispatchers.Default) {
        val result = searchUseCase.invoke(searchBarState.input.value, true)
        _items.value = result.doctors.map {
            Doctor(
                title = it.name,
                imageUri = it.imageUri,
                link = it.link,
                type = CatalogItemType.DOCTOR
            )
        }
    }
}