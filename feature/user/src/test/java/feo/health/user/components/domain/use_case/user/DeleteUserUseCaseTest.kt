package feo.health.user.components.domain.use_case.user

import feo.health.user.components.domain.repository.IUserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.runs
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DeleteUserUseCaseTest {

    private val userRepository: IUserRepository = mockk()
    private val deleteUserUseCase = DeleteUserUseCase(userRepository)

    @Test
    fun `invoke should call deleteUser on repository`() = runTest {
        // Arrange
        coEvery { userRepository.deleteUser() } just runs

        // Act
        deleteUserUseCase()

        // Assert
        coVerify(exactly = 1) { userRepository.deleteUser() }
    }
}
