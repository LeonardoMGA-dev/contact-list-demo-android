package com.demo.android.connectory.presentation.home

import com.demo.android.connectory.domain.entity.Employee

sealed interface HomeScreenUiState {
    object Loading : HomeScreenUiState
    data class Loaded(val employees: List<Employee>) : HomeScreenUiState
    data class Error(val errorMessage: String): HomeScreenUiState
}