package com.example.myapplicationskripsiiqbal3.di

import com.example.core.domain.repository.IEmployeeRepository
import com.example.core.domain.usecase.employee.EmployeeInteractor
import com.example.core.domain.usecase.employee.EmployeeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideEmployeeUseCase(repository: IEmployeeRepository) : EmployeeUseCase= EmployeeInteractor(repository)
}