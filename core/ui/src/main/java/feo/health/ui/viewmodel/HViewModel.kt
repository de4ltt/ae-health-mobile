package feo.health.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A base custom [ViewModel] that provides screen state stack-based navigation
 * history tracking and reactive state management.
 *
 * Implements standard push/pop/revert mechanics on screen states to allow ViewModels
 * to easily manage full screen loading overlays, state rolls, and back transitions.
 *
 * @param State The type representing screens UI states.
 * @param Event The type representing user actions or UI events.
 * @property initialState The initial visual state of the screen.
 */
abstract class HViewModel<State, Event>(
    var initialState: State
): ViewModel() {

    /**
     * Callback function to handle incoming UI events or user actions.
     *
     * @param event The triggered event containing payload parameters.
     */
    abstract fun onEvent(event: Event): Any

    /**
     * History stack holding previous screen states to enable rollbacks and back transitions.
     */
    private val stateStack: MutableStateFlow<List<State>> = MutableStateFlow<List<State>>(emptyList())

    /**
     * Internal mutable backing state flow.
     */
    private val _screenState: MutableStateFlow<State> =
        MutableStateFlow(initialState)

    /**
     * Read-only StateFlow representing the active screen state.
     */
    val screenState = _screenState.asStateFlow()

    /**
     * Returns true if the screen state history stack is empty.
     */
    val isEmpty: Boolean
        get() = stateStack.value.isEmpty()

    /**
     * Directly updates the screen state without modifying the history stack.
     */
    protected fun updateScreenState(state: State) {
        _screenState.value = state
    }

    /**
     * Reverts the active screen state to the last pushed state on the stack.
     * If the stack is empty, falls back to the [initialState].
     */
    protected fun revertScreenState() {
        _screenState.value = if (stateStack.value.isNotEmpty())
            stateStack.value.last()
        else initialState
    }

    /**
     * Pushes a new state to the active history stack and updates the screen state.
     */
    protected fun pushScreenState(state: State) {
        updateScreenState(state)
        stateStack.value = stateStack.value + state
    }

    /**
     * Pops the current state off the history stack and transitions back to the previous state.
     */
    protected fun onBack() {
        updateScreenState(popScreenState() ?: initialState)
    }

    /**
     * Pops the last state off the stack and returns the preceding state.
     *
     * @return The previous state in the history, or `null` if stack is empty.
     */
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