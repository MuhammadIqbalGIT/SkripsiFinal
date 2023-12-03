package com.example.core.di

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

//@Module
//@InstallIn(SingletonComponent::class)
object CoreModule {

//    @Singleton
//    @Provides
//    fun provideApiService(apiInterceptor: ApiInterceptor): ProjectApiService {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val client = OkHttpClient.Builder()
//            .addNetworkInterceptor(loggingInterceptor)
//            .addInterceptor(apiInterceptor)
//            .connectTimeout(5L, TimeUnit.MINUTES)
//            .readTimeout(5L, TimeUnit.MINUTES)
//            .build()
//
//        return Retrofit.Builder()
//            .baseUrl(BuildConfig.BASE_API)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build().create(ProjectApiService::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun provideApiServiceMerchant(apiInterceptor: ApiInterceptor): MerchantApiService {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val client = OkHttpClient.Builder()
//            .addNetworkInterceptor(loggingInterceptor)
//            .addInterceptor(apiInterceptor)
//            .connectTimeout(5L, TimeUnit.MINUTES)
//            .readTimeout(5L, TimeUnit.MINUTES)
//            .build()
//
//        return Retrofit.Builder()
//            .baseUrl(BuildConfig.BASE_API)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build().create(MerchantApiService::class.java)
//    }
//
//
//    @Singleton
//    @Provides
//    fun provideUserRepository(
//        source: UserDataSource
//    ): IUserRepository = UserRepository(source)
//
//
//    @Singleton
//    @Provides
//    fun provideUserDataSource(
//        apiService: ProjectApiService,
//        merchantApiService: MerchantApiService,
//        session: Session
//    ): UserDataSource =
//        UserDataSource(apiService, merchantApiService,session)
//
//
//
//    @Singleton
//    @Provides
//    fun provideQuotationRepository(
//        source: QuotationDataSource
//    ): IQuotationRepository = QuotationRepository(source)
//
//
//    @Singleton
//    @Provides
//    fun provideQuotationDataSource(
//        apiService: ProjectApiService,
//        merchantApiService: MerchantApiService,
//        session: Session
//    ): QuotationDataSource =
//        QuotationDataSource(apiService, merchantApiService,session)
//
//
//    @Singleton
//    @Provides
//    fun provideProductRepository(
//        source: ProductDataSource
//    ): IProductRepository = ProductRepository(source)
//
//
//    @Singleton
//    @Provides
//    fun provideProductDataSource(
//        apiService: ProjectApiService,
//        merchantApiService: MerchantApiService,
//        session: Session
//    ): ProductDataSource =
//        ProductDataSource(apiService, merchantApiService,session)

}