package com.demo.android.connectory.data.repository

import com.demo.android.connectory.data.networking.ConnectoryService
import com.demo.android.connectory.data.networking.dto.toDomainEntity
import com.demo.android.connectory.domain.entity.Employee
import com.demo.android.connectory.domain.repository.EmployeeRepository
import com.example.countify.util.Either
import com.example.countify.util.ErrorEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmployeeRepositoryImp(
    private val connectoryService: ConnectoryService,
) : EmployeeRepository {
    override suspend fun fetchEmployees(): Either<List<Employee>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = connectoryService.getEmployees()
                if (!response.isSuccessful) {
                    return@withContext Either.Error(ErrorEntity.HttpError(response.code()))
                }
                val body = response.body() ?: return@withContext Either.Error(ErrorEntity.EmptyBody)
                return@withContext Either.Success(body.employees.map { it.toDomainEntity() })
            } catch (e: Exception) {
                return@withContext Either.Error(ErrorEntity.UnknownError(e))
            }
        }
    }
}