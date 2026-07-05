package feo.health.user.components.domain.use_case.favourite

import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.domain.repository.IFavouritesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime

class GetFavouritesUseCaseTest {

    private val favouritesRepository: IFavouritesRepository = mockk()
    private val getFavouritesUseCase = GetFavouritesUseCase(favouritesRepository)

    @Test
    fun `invoke should call getFavourites on repository and return map`() = runTest {
        // Arrange
        val expectedMap = mapOf(
            "Drugs" to listOf(
                CatalogItemDomain(
                    name = "Aspirin",
                    link = "/aspirin",
                    type = "DRUG",
                    imageUri = null,
                    dateTime = LocalDateTime.of(2026, 1, 1, 12, 0)
                )
            )
        )
        coEvery { favouritesRepository.getFavourites() } returns expectedMap

        // Act
        val actualMap = getFavouritesUseCase()

        // Assert
        assertEquals(expectedMap, actualMap)
        coVerify(exactly = 1) { favouritesRepository.getFavourites() }
    }
}
