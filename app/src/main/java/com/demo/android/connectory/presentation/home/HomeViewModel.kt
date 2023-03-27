package com.demo.android.connectory.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.android.connectory.R
import com.demo.android.connectory.domain.usecase.FetchEmployeesUseCase
import com.example.countify.util.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchEmployeesUseCase: FetchEmployeesUseCase,
) : ViewModel() {

    private var searchJob: Job? = null

    private val _uiStateFlow = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Loading)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        fetchEmployees(showLoading = true)
    }

    fun searchDebounce(employeeName: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(DEFAULT_DEBOUNCE_TIME)
            fetchEmployees(employeeName = employeeName)
        }
    }

    fun fetchEmployees(
        employeeName: String = DEFAULT_EMPLOYEE_NAME,
        showLoading: Boolean = DEFAULT_SHOW_LOADING,
    ) {
        viewModelScope.launch {
            if (showLoading) {
                _uiStateFlow.value = HomeScreenUiState.Loading
            }
            when (val result = fetchEmployeesUseCase()) {
                is Either.Success -> {
                    _uiStateFlow.value = HomeScreenUiState.Loaded(result.value.filter {
                        it.fullName.lowercase().contains(employeeName.lowercase())
                    })
                }
                is Either.Error -> {
                    _uiStateFlow.value = HomeScreenUiState.Error(R.string.error_message)
                }
            }
        }
    }

    companion object {
        private const val DEFAULT_DEBOUNCE_TIME = 500L
        private const val DEFAULT_SHOW_LOADING = true
        private const val DEFAULT_EMPLOYEE_NAME = ""
    }

}