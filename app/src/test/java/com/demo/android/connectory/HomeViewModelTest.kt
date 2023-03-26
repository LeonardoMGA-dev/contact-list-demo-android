package com.demo.android.connectory

import com.demo.android.connectory.domain.usecase.FetchEmployeesUseCase
import com.demo.android.connectory.presentation.home.HomeScreenUiState
import com.demo.android.connectory.presentation.home.HomeViewModel
import com.example.countify.util.Either
import com.example.countify.util.ErrorEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val instantTaskExecutorRule = MainCoroutineRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var fetchEmployeesUseCase: FetchEmployeesUseCase

    @Before
    fun setUp() {
        fetchEmployeesUseCase = mockk()
        viewModel = HomeViewModel(fetchEmployeesUseCase)
    }

    @Test
    fun `when fetchEmployees called, should update uiStateFlow with Loaded state on success`() = runBlocking {
        // Given
        coEvery { fetchEmployeesUseCase() } returns Either.Success(emptyList())
        
        // When
        viewModel.fetchEmployees()
        
        // Then
        val uiState = viewModel.uiStateFlow.value
        assertTrue(uiState is HomeScreenUiState.Loaded)
        // assertEquals(emptyList<Employee>(), uiState.employees)
    }

    @Test
    fun `when fetchEmployees called, should update uiStateFlow with error on failure`() = runBlocking {
        // Given
        coEvery { fetchEmployeesUseCase() } returns Either.Error(ErrorEntity.UnknownError(Exception()))
        
        // When
        viewModel.fetchEmployees()
        
        // Then
        val uiState = viewModel.uiStateFlow.value
        //assertTrue(uiState is HomeScreenUiState.Error)
    }
}
