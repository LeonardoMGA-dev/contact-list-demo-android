package com.demo.android.connectory.presentation.home

import androidx.annotation.StringRes
import com.demo.android.connectory.domain.entity.Employee

sealed interface HomeScreenUiState {
    object Loading : HomeScreenUiState
    data class Loaded(val employees: List<Employee>) : HomeScreenUiState
    data class Error(@StringRes val errorMessage: Int): HomeScreenUiState
}