package feo.health.auth.presentation.viewmodel.companion

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal object AuthFieldsState {

    object SignIn {
        val email: MutableStateFlow<String> = MutableStateFlow("")
        val password: MutableStateFlow<String> = MutableStateFlow("")
    }

    object SignUp {
        val name: MutableStateFlow<String> = MutableStateFlow("")
        val email: MutableStateFlow<String> = MutableStateFlow("")
        val password: MutableStateFlow<String> = MutableStateFlow("")
        val dateOfBirth: MutableStateFlow<String> = MutableStateFlow("")
    }

}