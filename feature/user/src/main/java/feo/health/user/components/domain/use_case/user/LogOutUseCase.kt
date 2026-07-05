package feo.health.user.components.domain.use_case.user

import feo.health.user.components.domain.repository.IUserRepository
import javax.inject.Inject

/**
 * Use case for logging out the current user.
 *
 * @property userRepository The repository responsible for managing user data and session details.
 */
class LogOutUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    /**
     * Executes the use case to log out the user.
     */
    suspend operator fun invoke() = userRepository.logOut()
}