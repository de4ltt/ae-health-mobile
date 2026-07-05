package feo.health.user.components.domain.use_case.util.impl

import feo.health.user.components.domain.use_case.user.ChangePasswordUseCase
import feo.health.user.components.domain.use_case.user.DeleteUserUseCase
import feo.health.user.components.domain.use_case.user.GetUserInfoUseCase
import feo.health.user.components.domain.use_case.user.LogOutUseCase
import feo.health.user.components.domain.use_case.user.UpdateUserInfoUseCase
import feo.health.user.components.domain.use_case.util.IUserUseCases
import javax.inject.Inject

/**
 * Implementation of [IUserUseCases] containing all user-related use cases.
 *
 * @property getUserInfoUseCase Use case for retrieving the current user's profile information.
 * @property updateUserInfoUseCase Use case for updating the user's profile information.
 * @property deleteUserUseCase Use case for deleting the current user account permanently.
 * @property changePasswordUseCase Use case for changing the user's password.
 * @property logOutUseCase Use case for logging out the current user.
 */
data class UserUseCases @Inject constructor(
    override val getUserInfoUseCase: GetUserInfoUseCase,
    override val updateUserInfoUseCase: UpdateUserInfoUseCase,
    override val deleteUserUseCase: DeleteUserUseCase,
    override val changePasswordUseCase: ChangePasswordUseCase,
    override val logOutUseCase: LogOutUseCase
): IUserUseCases
