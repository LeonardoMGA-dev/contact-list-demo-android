package com.demo.android.connectory.presentation.home

import androidx.compose.runtime.MutableState
import com.demo.android.connectory.domain.entity.Employee

sealed interface HomeScreenUiState {
    object Loading : HomeScreenUiState
    data class Loaded(val employees: List<Employee>) : HomeScreenUiState
}