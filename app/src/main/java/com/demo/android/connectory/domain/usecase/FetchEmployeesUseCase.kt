package com.demo.android.connectory.domain.usecase

import com.demo.android.connectory.domain.entity.Employee
import com.demo.android.connectory.domain.repository.EmployeeRepository
import com.example.countify.util.Either

class FetchEmployeesUseCase(private val employeeRepository: EmployeeRepository) {
    suspend operator fun invoke(): Either<List<Employee>> = employeeRepository.fetchEmployees()
}