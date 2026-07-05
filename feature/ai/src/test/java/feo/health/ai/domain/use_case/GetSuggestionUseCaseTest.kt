package feo.health.ai.domain.use_case

import feo.health.ai.domain.model.request.FeatureSuggestionRequestDomain
import feo.health.ai.domain.model.response.FeatureSuggestionResponseDomain
import feo.health.ai.domain.repository.IAiRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetSuggestionUseCaseTest {

    private val aiRepository: IAiRepository = mockk()
    private val getSuggestionUseCase = GetSuggestionUseCase(aiRepository)

    @Test
    fun `invoke should call getSuggestion on repository and return result`() = runTest {
        // Arrange
        val request = FeatureSuggestionRequestDomain(
            input = "Healthy diet advice"
        )
        val expectedResponse = FeatureSuggestionResponseDomain(
            doctors = listOf("Nutritionist"),
            drugs = emptyList(),
            possibleDiseases = emptyMap(),
            generalAnswer = "Eat more vegetables and stay hydrated"
        )
        coEvery { aiRepository.getSuggestion(request) } returns expectedResponse

        // Act
        val actualResponse = getSuggestionUseCase(request)

        // Assert
        assertEquals(expectedResponse, actualResponse)
        coVerify(exactly = 1) { aiRepository.getSuggestion(request) }
    }
}
