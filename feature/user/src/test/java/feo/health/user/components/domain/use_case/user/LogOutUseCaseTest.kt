package feo.health.user.components.domain.use_case.user

import feo.health.user.components.domain.repository.IUserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.runs
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class LogOutUseCaseTest {

    private val userRepository: IUserRepository = mockk()
    private val logOutUseCase = LogOutUseCase(userRepository)

    @Test
    fun `invoke should call logOut on repository`() = runTest {
        // Arrange
        coEvery { userRepository.logOut() } just runs

        // Act
        logOutUseCase()

        // Assert
        coVerify(exactly = 1) { userRepository.logOut() }
    }
}
