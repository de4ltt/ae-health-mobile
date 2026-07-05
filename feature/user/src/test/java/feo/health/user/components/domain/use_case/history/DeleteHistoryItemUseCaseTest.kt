package feo.health.user.components.domain.use_case.history

import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.domain.repository.IHistoryRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.runs
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.LocalDateTime

class DeleteHistoryItemUseCaseTest {

    private val historyRepository: IHistoryRepository = mockk()
    private val deleteHistoryItemUseCase = DeleteHistoryItemUseCase(historyRepository)

    @Test
    fun `invoke should call deleteHistoryItem on repository`() = runTest {
        // Arrange
        val catalogItemDomain = CatalogItemDomain(
            name = "Doctor Smith",
            link = "/smith",
            type = "DOCTOR",
            imageUri = null,
            dateTime = LocalDateTime.of(2026, 1, 1, 12, 0)
        )
        coEvery { historyRepository.deleteHistoryItem(catalogItemDomain) } just runs

        // Act
        deleteHistoryItemUseCase(catalogItemDomain)

        // Assert
        coVerify(exactly = 1) { historyRepository.deleteHistoryItem(catalogItemDomain) }
    }
}
