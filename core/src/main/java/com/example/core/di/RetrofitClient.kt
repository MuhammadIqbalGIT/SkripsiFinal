package com.example.core.di

import com.example.core.data.remote.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    //jika pakai Emulator dan ada portnya
    private const val BASE_URL_WITH_PORT = "http://10.0.2.2:8080/rest_api/index.php/"


    //jika pakai emulator
    private const val BASE_URL = "http://10.0.2.2/rest_api/index.php/"


    //jika pakai device handphone
    private const val BASE_URL_HANDPHONE = "http://192.168.246.197/rest_api/index.php/"





    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_HANDPHONE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}
