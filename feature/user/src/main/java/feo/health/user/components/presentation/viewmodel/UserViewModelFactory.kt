package feo.health.user.components.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import feo.health.user.components.domain.use_case.util.IFavouriteUseCases
import feo.health.user.components.domain.use_case.util.IHistoryUseCases
import feo.health.user.components.domain.use_case.util.IUserUseCases
import javax.inject.Inject

/**
 * Factory class for creating instances of [UserViewModel].
 * Implements [ViewModelProvider.Factory] to handle dependency injection of required use cases.
 *
 * @property favouriteUseCases Use cases for retrieving and modifying favourites.
 * @property historyUseCases Use cases for retrieving and modifying history items.
 * @property userUseCases Use cases for general user account operations.
 */
class UserViewModelFactory
/**
 * Primary constructor for [UserViewModelFactory] injecting required use case boundaries.
 *
 * @param favouriteUseCases Use cases for managing favourite items.
 * @param historyUseCases Use cases for managing viewing history.
 * @param userUseCases Use cases for general user account operations.
 */
@Inject constructor(
    private val favouriteUseCases: IFavouriteUseCases,
    private val historyUseCases: IHistoryUseCases,
    private val userUseCases: IUserUseCases
) : ViewModelProvider.Factory {

    /**
     * Creates a new instance of the requested [ViewModel] class [T].
     *
     * @param modelClass The class type of the ViewModel to create.
     * @return A newly created [ViewModel] of type [T].
     * @throws IllegalArgumentException if the requested [modelClass] is not assignable from [UserViewModel].
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(
                favouriteUseCases = favouriteUseCases,
                userUseCases= userUseCases,
                historyUseCases = historyUseCases
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}