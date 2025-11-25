package feo.health.user.components.domain.use_case.user

import feo.health.user.components.domain.model.ChangePasswordDomain
import feo.health.user.components.domain.repository.IUserRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    suspend operator fun invoke(
        changePasswordDomain: ChangePasswordDomain
    ) = userRepository.changePassword(changePasswordDomain)
}