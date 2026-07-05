package feo.health.user.components.domain.use_case.favourite

import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.components.domain.repository.IFavouritesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.runs
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.LocalDateTime

class AddFavouriteUseCaseTest {

    private val favouritesRepository: IFavouritesRepository = mockk()
    private val addFavouriteUseCase = AddFavouriteUseCase(favouritesRepository)

    @Test
    fun `invoke should call addFavourite on repository`() = runTest {
        // Arrange
        val catalogItemDomain = CatalogItemDomain(
            name = "Aspirin",
            link = "/aspirin",
            type = "DRUG",
            imageUri = null,
            dateTime = LocalDateTime.of(2026, 1, 1, 12, 0)
        )
        coEvery { favouritesRepository.addFavourite(catalogItemDomain) } just runs

        // Act
        addFavouriteUseCase(catalogItemDomain)

        // Assert
        coVerify(exactly = 1) { favouritesRepository.addFavourite(catalogItemDomain) }
    }
}
