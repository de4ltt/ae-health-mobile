package feo.health.user.components.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import feo.health.user.components.domain.use_case.util.IFavouriteUseCases
import feo.health.user.components.domain.use_case.util.IHistoryUseCases
import feo.health.user.components.domain.use_case.util.IUserUseCases
import javax.inject.Inject

class UserViewModelFactory @Inject constructor(
    private val favouriteUseCases: IFavouriteUseCases,
    private val historyUseCases: IHistoryUseCases,
    private val userUseCases: IUserUseCases
) : ViewModelProvider.Factory {

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