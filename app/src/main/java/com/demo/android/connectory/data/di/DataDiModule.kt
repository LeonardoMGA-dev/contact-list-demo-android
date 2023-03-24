package com.demo.android.connectory.data.di

import com.demo.android.connectory.data.networking.ConnectoryService
import com.demo.android.connectory.data.repository.EmployeeRepositoryImp
import com.demo.android.connectory.domain.repository.EmployeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataDiModule {

    @Singleton
    @Provides
    fun provideConnectoryService(): ConnectoryService {
        return Retrofit.Builder().baseUrl("https://s3.amazonaws.com/sq-mobile-interview/").build()
            .create(ConnectoryService::class.java)
    }

    @Provides
    fun provideRepository(connectoryService: ConnectoryService): EmployeeRepository {
        return EmployeeRepositoryImp(connectoryService)
    }

}