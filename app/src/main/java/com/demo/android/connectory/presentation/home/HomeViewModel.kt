package com.demo.android.connectory.presentation.home

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.android.connectory.domain.entity.Employee
import com.demo.android.connectory.domain.usecase.FetchEmployeesUseCase
import com.example.countify.util.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchEmployeesUseCase: FetchEmployeesUseCase,
) : ViewModel() {

    val employees = mutableStateListOf<Employee>()

    fun fetchEmployees() {
        viewModelScope.launch {
            when (val result = fetchEmployeesUseCase()) {
                is Either.Success -> {
                    employees.clear()
                    employees.addAll(result.value)
                }
                is Either.Error -> {
                    Log.i("HomeViewModel", "fetchEmployees: $result")
                }
            }
        }
    }

}