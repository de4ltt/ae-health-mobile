package feo.health.user.components.domain.use_case.user

import feo.health.user.components.domain.model.UserDomain
import feo.health.user.components.domain.repository.IUserRepository
import javax.inject.Inject

class UpdateUserInfoUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    suspend operator fun invoke(user: UserDomain) = userRepository.updateUserInfo(user)
}