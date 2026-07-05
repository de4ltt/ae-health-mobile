package feo.health.user.components.domain.use_case.util

import feo.health.user.components.domain.use_case.user.ChangePasswordUseCase
import feo.health.user.components.domain.use_case.user.DeleteUserUseCase
import feo.health.user.components.domain.use_case.user.GetUserInfoUseCase
import feo.health.user.components.domain.use_case.user.LogOutUseCase
import feo.health.user.components.domain.use_case.user.UpdateUserInfoUseCase

/**
 * Interface representing the wrapper/container for all user-related use cases.
 */
interface IUserUseCases {
    /**
     * Use case for retrieving the current user's profile information.
     */
    val getUserInfoUseCase: GetUserInfoUseCase

    /**
     * Use case for updating the user's profile information.
     */
    val updateUserInfoUseCase: UpdateUserInfoUseCase

    /**
     * Use case for deleting the current user account permanently.
     */
    val deleteUserUseCase: DeleteUserUseCase

    /**
     * Use case for logging out the current user.
     */
    val logOutUseCase: LogOutUseCase

    /**
     * Use case for changing the user's password.
     */
    val changePasswordUseCase: ChangePasswordUseCase
}