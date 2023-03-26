package com.demo.android.connectory.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.android.connectory.domain.usecase.FetchEmployeesUseCase
import com.example.countify.util.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchEmployeesUseCase: FetchEmployeesUseCase,
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Loading)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        fetchEmployees(showLoading = true)
    }

    fun fetchEmployees(employeeName: String = "", showLoading: Boolean = true) {
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
                    // TODO: Handle error case
                }
            }
        }
    }

}