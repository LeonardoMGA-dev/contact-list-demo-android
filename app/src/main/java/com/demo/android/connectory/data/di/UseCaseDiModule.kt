package com.demo.android.connectory.data.di

import com.demo.android.connectory.domain.repository.EmployeeRepository
import com.demo.android.connectory.domain.usecase.FetchEmployeesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseDiModule {
    @Provides
    fun provideFetchEmployeesUseCase(employeeRepository: EmployeeRepository): FetchEmployeesUseCase {
        return FetchEmployeesUseCase(employeeRepository)
    }
}
