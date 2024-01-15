package com.example.core.data.remote.network

import com.example.core.data.remote.request.LoginRequest
import com.example.core.data.remote.response.ApiResponseNew
import com.example.core.data.remote.response.ProductApiResponse
import com.example.core.domain.model.PenggunaModel
import com.example.core.domain.model.login.LoginModel
import com.example.core.domain.model.product.ProductModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {


    /**
     * Get Pengguna
     **/
    @GET("pengguna")
    fun getPenggunaCall(): Call<ArrayList<PenggunaModel>>

    /**
     * User Login
     **/
    @POST("pengguna/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginModel>


    /**
     * Get Product
     **/
    @GET("product")
    fun getProduct(): Call<ArrayList<ProductModel>>


    /**
     * Get Product Detail
     **/
    @GET("product/detail")
    fun getProductDetail(@Query("id") productId: Int): Call<ApiResponseNew<ProductApiResponse>>

}
