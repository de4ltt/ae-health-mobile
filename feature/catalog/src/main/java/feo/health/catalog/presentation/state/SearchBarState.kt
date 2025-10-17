package feo.health.catalog.presentation.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchBarState {

    val input = MutableStateFlow("")

    private val _isButtonVisible: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isButtonVisible = _isButtonVisible.asStateFlow()

    private val _isFiltersVisible: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFiltersVisible = _isFiltersVisible.asStateFlow()

    fun onInput(input: String) {
        _isButtonVisible.value = input.isNotEmpty()
        this.input.value = input
    }

    fun onFilterButtonClick() {
        _isFiltersVisible.value = !_isFiltersVisible.value
    }
}