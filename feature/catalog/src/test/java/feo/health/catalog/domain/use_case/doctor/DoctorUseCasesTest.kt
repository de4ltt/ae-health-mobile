package feo.health.catalog.domain.use_case.doctor

import feo.health.catalog.domain.model.DoctorDomain
import feo.health.catalog.domain.repository.IDoctorRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DoctorUseCasesTest {

    private val doctorRepository: IDoctorRepository = mockk()

    private val getDoctorInfoUseCase = GetDoctorInfoUseCase(doctorRepository)
    private val getDoctorsBySpecialityUseCase = GetDoctorsBySpecialityUseCase(doctorRepository)
    private val getDoctorsUseCase = GetDoctorsUseCase(doctorRepository)

    private val sampleDoctor = DoctorDomain(
        name = "Dr. Smith",
        link = "/smith",
        specialities = null,
        experience = 10,
        imageUri = null,
        rating = 4.5,
        itemType = "doctor",
        reviews = null
    )

    @Test
    fun `GetDoctorInfoUseCase should call getDoctorInfo on repository`() = runTest {
        val expected = sampleDoctor
        coEvery { doctorRepository.getDoctorInfo("/smith") } returns expected

        val actual = getDoctorInfoUseCase("/smith")

        assertEquals(expected, actual)
        coVerify(exactly = 1) { doctorRepository.getDoctorInfo("/smith") }
    }

    @Test
    fun `GetDoctorsBySpecialityUseCase should call getDoctorsBySpeciality on repository`() = runTest {
        val expected = listOf(sampleDoctor)
        coEvery { doctorRepository.getDoctorsBySpeciality("Cardiology") } returns expected

        val actual = getDoctorsBySpecialityUseCase("Cardiology")

        assertEquals(expected, actual)
        coVerify(exactly = 1) { doctorRepository.getDoctorsBySpeciality("Cardiology") }
    }

    @Test
    fun `GetDoctorsUseCase should call getDoctors on repository`() = runTest {
        val expected = listOf(sampleDoctor)
        coEvery { doctorRepository.getDoctors("Smith") } returns expected

        val actual = getDoctorsUseCase("Smith")

        assertEquals(expected, actual)
        coVerify(exactly = 1) { doctorRepository.getDoctors("Smith") }
    }
}
