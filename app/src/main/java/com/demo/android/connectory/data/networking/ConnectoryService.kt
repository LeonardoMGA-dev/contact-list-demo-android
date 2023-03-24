package com.demo.android.connectory.data.networking

import com.demo.android.connectory.data.networking.dto.GetEmployeesResponseDto
import retrofit2.Response

interface ConnectoryService {
    suspend fun getEmployees(): Response<GetEmployeesResponseDto>
}