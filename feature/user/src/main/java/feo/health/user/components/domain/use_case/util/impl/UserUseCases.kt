package feo.health.user.components.domain.use_case.util.impl

import feo.health.user.components.domain.use_case.user.ChangePasswordUseCase
import feo.health.user.components.domain.use_case.user.DeleteUserUseCase
import feo.health.user.components.domain.use_case.user.GetUserInfoUseCase
import feo.health.user.components.domain.use_case.user.LogOutUseCase
import feo.health.user.components.domain.use_case.user.UpdateUserInfoUseCase
import feo.health.user.components.domain.use_case.util.IUserUseCases
import javax.inject.Inject

data class UserUseCases @Inject constructor(
    override val getUserInfoUseCase: GetUserInfoUseCase,
    override val updateUserInfoUseCase: UpdateUserInfoUseCase,
    override val deleteUserUseCase: DeleteUserUseCase,
    override val changePasswordUseCase: ChangePasswordUseCase,
    override val logOutUseCase: LogOutUseCase
): IUserUseCases
