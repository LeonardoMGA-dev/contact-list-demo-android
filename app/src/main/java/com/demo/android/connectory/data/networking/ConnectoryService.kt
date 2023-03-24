package com.demo.android.connectory.data.networking

import com.demo.android.connectory.data.networking.dto.GetEmployeesResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ConnectoryService {
    @GET("employees.json")
    suspend fun getEmployees(): Response<GetEmployeesResponseDto>
}