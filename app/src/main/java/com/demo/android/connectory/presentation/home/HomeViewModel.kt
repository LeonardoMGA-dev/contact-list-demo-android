package com.demo.android.connectory.presentation.home

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.android.connectory.domain.usecase.FetchEmployeesUseCase
import com.example.countify.util.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchEmployeesUseCase: FetchEmployeesUseCase,
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Loading)
    val uiStateFlow = _uiStateFlow.asStateFlow()
    init {
        fetchEmployees()
    }

    @VisibleForTesting
    fun fetchEmployees() {
        viewModelScope.launch {
            _uiStateFlow.value = HomeScreenUiState.Loading
            when (val result = fetchEmployeesUseCase()) {
                is Either.Success -> {
                    _uiStateFlow.value = HomeScreenUiState.Loaded(result.value)
                }
                is Either.Error -> {
                    Log.i("HomeViewModel", "fetchEmployees: $result")
                }
            }
        }
    }

}