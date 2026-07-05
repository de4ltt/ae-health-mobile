package feo.health.auth.presentation.viewmodel.companion

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Local memory buffer caching user input credentials details during login and registration typing events.
 */
internal object AuthFieldsState {

    /**
     * Cache properties holding typed login credentials information.
     */
    object SignIn {
        /**
         * Cached login email value flow.
         */
        val email: MutableStateFlow<String> = MutableStateFlow("")

        /**
         * Cached login password value flow.
         */
        val password: MutableStateFlow<String> = MutableStateFlow("")
    }

    /**
     * Cache properties holding typed registration credentials information.
     */
    object SignUp {
        /**
         * Cached registration name value flow.
         */
        val name: MutableStateFlow<String> = MutableStateFlow("")

        /**
         * Cached registration email value flow.
         */
        val email: MutableStateFlow<String> = MutableStateFlow("")

        /**
         * Cached registration password value flow.
         */
        val password: MutableStateFlow<String> = MutableStateFlow("")

        /**
         * Cached registration date of birth string value flow.
         */
        val dateOfBirth: MutableStateFlow<String> = MutableStateFlow("")
    }
}