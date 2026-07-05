package feo.health.user.components.domain.use_case.history

import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.domain.repository.IHistoryRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime

class GetHistoryUseCaseTest {

    private val historyRepository: IHistoryRepository = mockk()
    private val getHistoryUseCase = GetHistoryUseCase(historyRepository)

    @Test
    fun `invoke should call getHistory on repository and return map`() = runTest {
        // Arrange
        val expectedMap = mapOf(
            "Doctors" to listOf(
                CatalogItemDomain(
                    name = "Doctor Smith",
                    link = "/smith",
                    type = "DOCTOR",
                    imageUri = null,
                    dateTime = LocalDateTime.of(2026, 1, 1, 12, 0)
                )
            )
        )
        coEvery { historyRepository.getHistory() } returns expectedMap

        // Act
        val actualMap = getHistoryUseCase()

        // Assert
        assertEquals(expectedMap, actualMap)
        coVerify(exactly = 1) { historyRepository.getHistory() }
    }
}
