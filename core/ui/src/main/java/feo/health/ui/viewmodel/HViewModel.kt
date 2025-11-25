package feo.health.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class HViewModel<State, Event>(
    var initialState: State
): ViewModel() {

    abstract fun onEvent(event: Event): Any

    private val stateStack: MutableStateFlow<List<State>> = MutableStateFlow<List<State>>(emptyList())

    private val _screenState: MutableStateFlow<State> =
        MutableStateFlow(initialState)
    val screenState = _screenState.asStateFlow()

    val isEmpty: Boolean
        get() = stateStack.value.isEmpty()

    protected fun updateScreenState(state: State) = viewModelScope.launch(Dispatchers.Default) {
        _screenState.value = state
    }

    protected fun revertScreenState() = viewModelScope.launch(Dispatchers.Default) {
        _screenState.value = if (stateStack.value.isNotEmpty())
            stateStack.value.last()
        else initialState
    }

    protected fun pushScreenState(state: State) = viewModelScope.launch(Dispatchers.Default) {
        updateScreenState(state)
        stateStack.value = stateStack.value + state
    }

    protected fun onBack() = viewModelScope.launch(Dispatchers.IO) {
        updateScreenState(popScreenState() ?: initialState)
    }

    protected fun popScreenState(): State? {
        val current = stateStack.value
        if (current.isEmpty()) return null
        val newList = current.dropLast(1)
        stateStack.value = newList
        return newList.lastOrNull()
    }

    init {
        viewModelScope.launch {
            _screenState.collectLatest {
                Log.d("CATALOG_SCREEN_STATE", "$it")
            }
        }
    }
}