package feo.health.catalog.domain.use_case.service

import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.domain.model.ServiceDomain
import feo.health.catalog.domain.repository.IServicesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ServiceUseCasesTest {

    private val servicesRepository: IServicesRepository = mockk()

    private val getClinicsByServiceUseCase = GetClinicsByServiceUseCase(servicesRepository)
    private val getServicesUseCase = GetServicesUseCase(servicesRepository)

    @Test
    fun `GetClinicsByServiceUseCase should call getClinicsByService on repository`() = runTest {
        val expected = listOf(
            ClinicDomain(
                name = "Clinic A",
                link = "/a",
                address = "123 St",
                phoneNumber = "123",
                imageUri = null,
                itemType = "clinic",
                reviews = null
            )
        )
        coEvery { servicesRepository.getClinicsByService("/service") } returns expected

        val actual = getClinicsByServiceUseCase("/service")

        assertEquals(expected, actual)
        coVerify(exactly = 1) { servicesRepository.getClinicsByService("/service") }
    }

    @Test
    fun `GetServicesUseCase should call getServices on repository`() = runTest {
        val expected = listOf(
            ServiceDomain(
                name = "Service A",
                link = "/service",
                itemType = "service"
            )
        )
        coEvery { servicesRepository.getServices("q") } returns expected

        val actual = getServicesUseCase("q")

        assertEquals(expected, actual)
        coVerify(exactly = 1) { servicesRepository.getServices("q") }
    }
}
