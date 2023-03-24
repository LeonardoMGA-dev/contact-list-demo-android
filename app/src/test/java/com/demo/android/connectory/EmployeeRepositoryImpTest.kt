package com.demo.android.connectory

import com.demo.android.connectory.data.networking.ConnectoryService
import com.demo.android.connectory.data.networking.dto.EmployeeDto
import com.demo.android.connectory.data.networking.dto.GetEmployeesResponseDto
import com.demo.android.connectory.data.repository.EmployeeRepositoryImp
import com.demo.android.connectory.domain.repository.EmployeeRepository
import com.example.countify.util.Either
import com.example.countify.util.ErrorEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class EmployeeRepositoryImpTest {

    private lateinit var employeeRepository: EmployeeRepository
    private lateinit var connectoryService: ConnectoryService

    @Before
    fun setup() {
        connectoryService = mockk()
        employeeRepository = EmployeeRepositoryImp(connectoryService)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchEmployees returns success`() = runTest {
        // given
        val employee1 = EmployeeDto(
            "123",
            "John Smith",
            "555-555-5555",
            "john.smith@example.com",
            "A short bio",
            "http://example.com/photo_small.jpg",
            "http://example.com/photo_large.jpg",
            "Seller",
            "full_time"
        )
        val employee2 = EmployeeDto(
            "456",
            "Jane Doe",
            "555-555-1234",
            "jane.doe@example.com",
            "A short bio",
            "http://example.com/photo_small.jpg",
            "http://example.com/photo_large.jpg",
            "Seller",
            "full_time"
        )
        val employeeList = listOf(employee1, employee2)
        val responseBody = GetEmployeesResponseDto(employeeList)
        coEvery { connectoryService.getEmployees() } returns Response.success(responseBody)

        // when
        val result = employeeRepository.fetchEmployees()

        // then
        coVerify { connectoryService.getEmployees() }
        assertTrue(result is Either.Success)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchEmployees returns error when response is not successful`() = runTest {
        // given
        val httpErrorCode = 404
        coEvery { connectoryService.getEmployees() } returns Response.error(
            httpErrorCode,
            "".toResponseBody("text/plain".toMediaTypeOrNull())
        )

        // when
        val result = employeeRepository.fetchEmployees()

        // then
        coVerify { connectoryService.getEmployees() }
        assertTrue(result is Either.Error)
        assertTrue((result as Either.Error).error is ErrorEntity.HttpError)
        assertEquals(httpErrorCode, (result.error as ErrorEntity.HttpError).httpCode)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchEmployees returns error when response body is empty`() = runTest {
        // given
        coEvery { connectoryService.getEmployees() } returns Response.success(null)

        // when
        val result = employeeRepository.fetchEmployees()

        // then
        coVerify { connectoryService.getEmployees() }
        assertTrue(result is Either.Error)
        assertTrue((result as Either.Error).error is ErrorEntity.EmptyBody)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchEmployees returns error when an exception is thrown`() = runTest {
        // given
        val exception = IOException("Network error")
        coEvery { connectoryService.getEmployees() } throws exception

        // when
        val result = employeeRepository.fetchEmployees()

        // then
        coVerify { connectoryService.getEmployees() }
        assertTrue(result is Either.Error)
        assertTrue((result as Either.Error).error is ErrorEntity.UnknownError)
        assertEquals(exception, (result.error as ErrorEntity.UnknownError).exception)
    }
}
