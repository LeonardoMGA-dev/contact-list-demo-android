package com.demo.android.connectory.domain.repository

import com.demo.android.connectory.domain.Employee
import com.example.countify.util.Either

interface EmployeeRepository {
    suspend fun fetchEmployees(): Either<List<Employee>>
}