package feo.health.user.components.domain.use_case.user

import feo.health.user.components.domain.repository.IUserRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    suspend operator fun invoke() = userRepository.getUserInfo()
}