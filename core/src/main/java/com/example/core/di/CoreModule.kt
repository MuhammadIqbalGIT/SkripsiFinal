package com.example.core.di

import com.example.core.data.local.session.Session
import com.example.core.data.remote.datasource.EmployeeDataSource
import com.example.core.data.remote.network.ApiInterceptor
import com.example.core.data.remote.network.ApiService
import com.example.core.data.repository.EmployeeRepository
import com.example.core.domain.repository.IEmployeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {


//TODO : UNTUK NEMBAK KE EMULATOR (PAKAI YANG PORT SAJA SOALNYA AKSES MYSQL HARUS MENGGUNAKAN PORT)
//TODO : INI MENGGUNAKAN IP EMULATOR (IP DEFAULT EMULATOR)
//.baseUrl("http://10.0.2.2/server_api/index.php/ServerApi/")
//.baseUrl("http://10.0.2.2:8080/server_api/index.php/ServerApi/")


//TODO : UNTUK NEMBAK KE DEVICE (PAKAI YANG PORT SAJA SOALNYA AKSES MYSQL HARUS MENGGUNAKAN PORT)
//TODO : INI MENGGUNAKAN IP LAPTOP
//.baseUrl("http://192.168.127.197:8080/server_api/index.php/ServerApi/")

    private const val BASE_URL = "http://10.0.2.2:8080/rest_api/index.php/"

    @Singleton
    @Provides
    fun provideApiService(apiInterceptor: ApiInterceptor): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .addInterceptor(apiInterceptor)
            .connectTimeout(5L, TimeUnit.MINUTES)
            .readTimeout(5L, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            // .baseUrl("https://localhost:44333/api/Employee/")
            .baseUrl(BASE_URL)
            //   .baseUrl("http://10.0.2.2:8080/server_api/index.php/ServerApi/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideEmployeeRepository(
        source: EmployeeDataSource
    ): IEmployeeRepository = EmployeeRepository(source)


    @Singleton
    @Provides
    fun provideEmployeeDataSource(
        apiService: ApiService,
        sessionManager: Session
    ): EmployeeDataSource =
        EmployeeDataSource(apiService, sessionManager)


}