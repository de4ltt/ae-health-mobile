package feo.health.ai.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import feo.health.ai.domain.use_case.util.IAiUseCases
import javax.inject.Inject

/**
 * Custom Factory class responsible for initializing [AiViewModel] instances with correct dependencies.
 * Extends [ViewModelProvider.Factory] interface contract.
 *
 * @property aiUseCases Presentation use cases helper utility.
 */
class AiViewModelFactory @Inject constructor(
    private val aiUseCases: IAiUseCases
) : ViewModelProvider.Factory {

    /**
     * Instantiates the requested ViewModel type subclass.
     *
     * @param modelClass The class type representation to instantiate.
     * @return Initialized ViewModel subclass casting instance.
     * @throws IllegalArgumentException If an unsupported ViewModel class type is passed.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AiViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AiViewModel(
                aiUseCases = aiUseCases
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}