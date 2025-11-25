package feo.health.user.components.domain.use_case.user

import feo.health.user.components.data.repository.UserRepository
import feo.health.user.components.domain.repository.IUserRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    suspend operator fun invoke() = userRepository.logOut()
}