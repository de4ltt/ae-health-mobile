package feo.health.user.components.domain.use_case.user

import feo.health.user.components.domain.repository.IUserRepository
import javax.inject.Inject

/**
 * Use case for deleting the current user account permanently.
 *
 * @property userRepository The repository responsible for managing user data.
 */
class DeleteUserUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    /**
     * Executes the use case to delete the user.
     */
    suspend operator fun invoke() = userRepository.deleteUser()
}