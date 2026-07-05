package feo.health.auth.domain.use_case

import feo.health.auth.domain.model.SignInDomain
import feo.health.auth.domain.repository.IAuthRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

class SignInUseCaseTest {

    private val authRepository: IAuthRepository = mockk()
    private val signInUseCase = SignInUseCase(authRepository)

    @Test
    fun `invoke should call signIn on repository and return result`() = runTest {
        // Arrange
        val signInDomain = SignInDomain(email = "test@example.com", password = "password123")
        coEvery { authRepository.signIn(signInDomain) } returns true

        // Act
        val result = signInUseCase(signInDomain)

        // Assert
        assertTrue(result)
        coVerify(exactly = 1) { authRepository.signIn(signInDomain) }
    }
}
