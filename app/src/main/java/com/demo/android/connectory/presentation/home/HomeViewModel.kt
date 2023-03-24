package com.demo.android.connectory.presentation.home

import androidx.lifecycle.ViewModel
import com.demo.android.connectory.domain.usecase.FetchEmployeesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchEmployeesUseCase: FetchEmployeesUseCase,
) : ViewModel() {

}