package feo.health.user.components.domain.use_case.util

import feo.health.user.components.domain.use_case.user.ChangePasswordUseCase
import feo.health.user.components.domain.use_case.user.DeleteUserUseCase
import feo.health.user.components.domain.use_case.user.GetUserInfoUseCase
import feo.health.user.components.domain.use_case.user.LogOutUseCase
import feo.health.user.components.domain.use_case.user.UpdateUserInfoUseCase

interface IUserUseCases {
    val getUserInfoUseCase: GetUserInfoUseCase
    val updateUserInfoUseCase: UpdateUserInfoUseCase
    val deleteUserUseCase: DeleteUserUseCase
    val logOutUseCase: LogOutUseCase
    val changePasswordUseCase: ChangePasswordUseCase
}