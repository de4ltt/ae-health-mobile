package feo.health.catalog.presentation.viewmodel.companion

import androidx.compose.runtime.Composable
import feo.health.ui.resource.HStrings
import feo.health.ui.resource.HStrings.capitalize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

object SearchBarState {

    val input = MutableStateFlow("")

    private val _isInputAllowed: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isInputAllowed = _isInputAllowed.asStateFlow()

    private val _isButtonVisible: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isButtonVisible = _isButtonVisible.asStateFlow()

    private val _isFiltersVisible: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFiltersVisible = _isFiltersVisible.asStateFlow()

    fun onInput(input: String) {
        _isButtonVisible.value = input.isNotEmpty() && _isInputAllowed.value
        this.input.value = input
    }

    fun disableInput() = CoroutineScope(Dispatchers.IO).launch {
        delay(1000)
        _isInputAllowed.value = false
    }

    fun enableInput() = CoroutineScope(Dispatchers.IO).launch {
        delay(1000)
        _isInputAllowed.value = true
    }

    fun onFilterButtonClick() {
        _isFiltersVisible.value = !_isFiltersVisible.value && _isInputAllowed.value
    }

    object FiltersState {

        private val _allowedType: MutableStateFlow<List<Filters.Type>> =
            MutableStateFlow(emptyList())
        val allowedType = _allowedType.asStateFlow()

        private val _allowedSorting: MutableStateFlow<List<Filters.Sorting>> =
            MutableStateFlow(emptyList())
        val allowedSorting = _allowedSorting.asStateFlow()

        private val _allowedRadius: MutableStateFlow<List<Filters.Radius>> =
            MutableStateFlow(emptyList())
        val allowedRadius = _allowedRadius.asStateFlow()

        private val _selectedType: MutableStateFlow<List<Filters.Type>> =
            MutableStateFlow(emptyList())
        val selectedType = _selectedType.asStateFlow()

        private val _selectedSorting: MutableStateFlow<List<Filters.Sorting>> =
            MutableStateFlow(emptyList())
        val selectedSorting = _selectedSorting.asStateFlow()

        private val _selectedRadius: MutableStateFlow<List<Filters.Radius>> =
            MutableStateFlow(emptyList())
        val selectedRadius = _selectedRadius.asStateFlow()

        fun getActivatedTypes(): List<Filters.Type> = _selectedType.value

        private fun updateFilters() {
            _allowedType.value = Filters.Type.entries.filter { it.allowed() }
            _allowedSorting.value = Filters.Sorting.entries.filter { it.allowed() }
            _allowedRadius.value = Filters.Radius.entries.filter { it.allowed() }
        }

        fun switchFilter(filter: Filters) {
            fun <T: Filters> List<T>.addOrDelete(item: T): List<T> {
                val result = this.toMutableList()
                if (result.contains(item))
                    result.remove(item)
                else result.add(item)
                return result.toList()
            }
            when (filter) {
                is Filters.Radius -> _selectedRadius.value = _selectedRadius.value.addOrDelete(filter)
                is Filters.Type -> _selectedType.value = _selectedType.value.addOrDelete(filter)
                is Filters.Sorting -> _selectedSorting.value = _selectedSorting.value.addOrDelete(filter)
            }
            updateFilters()
        }

        init {
            _selectedType.value = listOf(Filters.Type.Doctors)
            updateFilters()
        }
    }

    sealed interface Filters {

        val title: String
            @Composable get
        val onClick: (Filters) -> Unit
            get() = { FiltersState.switchFilter(it) }
        val allowed: () -> Boolean

        companion object {
            val entries
                @Composable
                get() = mapOf(
                    Type.groupTitle to Type.entries,
                    Sorting.groupTitle to Sorting.entries,
                    Radius.groupTitle to Radius.entries
                )
        }

        abstract class Type : Filters {
            override val allowed: () -> Boolean
                get() = { true }

            object Clinics : Type() {
                override val title: String
                    @Composable get() = HStrings.clinics.capitalize()
            }

            object Doctors : Type() {
                override val title: String
                    @Composable get() = HStrings.doctors.capitalize()
            }

            object Services : Type() {
                override val title: String
                    @Composable get() = HStrings.services.capitalize()
            }

            object Pharmacies : Type() {
                override val title: String
                    @Composable get() = HStrings.pharmacies.capitalize()
            }

            companion object {
                val entries
                    get() = listOf(Clinics, Doctors, Services)
                val groupTitle: String
                    @Composable get() = HStrings.type.capitalize()
            }
        }

        sealed class Sorting : Filters {
            override val allowed: () -> Boolean
                get() = { true }

            object Title : Sorting() {
                override val title: String
                    @Composable get() = HStrings.title.capitalize()
            }

            object Rating : Sorting() {
                override val title: String
                    @Composable get() = HStrings.rating.capitalize()
            }

            object Experience : Sorting() {
                override val allowed: () -> Boolean
                    get() = {
                        SearchBarState.FiltersState.selectedType.value.let {
                            it.contains(Type.Clinics) || it.contains(Type.Doctors)
                        }
                    }
                override val title: String
                    @Composable get() = HStrings.experience.capitalize()
            }

            object Distance : Sorting() {
                override val allowed: () -> Boolean
                    get() = {
                        SearchBarState.FiltersState.selectedType.value.contains(Type.Pharmacies)
                    }
                override val title: String
                    @Composable get() = HStrings.distance.capitalize()
            }

            companion object {
                val entries
                    get() = listOf(Title, Rating, Experience, Distance)
                val groupTitle: String
                    @Composable get() = HStrings.sorting.capitalize()
            }
        }

        sealed class Radius : Filters {
            override val allowed: () -> Boolean
                get() = { SearchBarState.FiltersState.selectedType.value.contains(Type.Pharmacies) }

            object Any : Radius() {
                override val title: String
                    @Composable get() = HStrings.any.capitalize()
            }

            object FiveHundredMeters : Radius() {
                override val title: String
                    @Composable get() = HStrings.fiveHundredM.capitalize()
            }

            object OneKilometer : Radius() {
                override val title: String
                    @Composable get() = HStrings.oneKm.capitalize()
            }

            object TwoKilometers : Radius() {
                override val title: String
                    @Composable get() = HStrings.twoKm.capitalize()
            }

            companion object {
                val entries
                    get() = listOf(Any, FiveHundredMeters, OneKilometer, TwoKilometers)
                val groupTitle: String
                    @Composable get() = HStrings.searchRadius.capitalize()
            }
        }
    }
}