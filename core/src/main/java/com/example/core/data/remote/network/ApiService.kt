package com.example.core.data.remote.network

import com.example.core.data.remote.request.AddNewProductRequest
import com.example.core.data.remote.request.LoginRequest
import com.example.core.data.remote.request.UpdateProductRequest
import com.example.core.data.remote.response.ApiResponseNew
import com.example.core.domain.model.PenggunaModel
import com.example.core.domain.model.ProductApiResponse
import com.example.core.domain.model.addNewProduct.ProductData
import com.example.core.domain.model.brand.BrandModel
import com.example.core.domain.model.deleteProduct.ApiResponse
import com.example.core.domain.model.login.LoginModel
import com.example.core.domain.model.product.ApiResponseProduct
import com.example.core.domain.model.product.ProductModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
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
    fun getProduct(@Query("nama") search: String): Call<ApiResponseProduct>


    /**
     * Get Product Detail
     **/
    @GET("product/detail")
    fun getProductDetail(@Query("id") productId: Int): Call<ApiResponseNew<ProductApiResponse>>

    /**
     * Add New Product
     **/
    @POST("product")
    fun addProduct(@Body productRequest: AddNewProductRequest): Call<ProductData>


    /**
     * Delete Product
     **/
    @DELETE("product")
    fun deleteProduct(@Query("id") productId: Int): Call<ApiResponse>


    /**
     * Update Product
     **/
    @PUT("product")
    fun updateProduct(@Body productRequest: UpdateProductRequest): Call<ProductData>

    /**
     * Get Brand
     **/
    @GET("brand")
    fun getBrand(@Query("NamaBrand") namaBrand: String): Call<com.example.core.domain.model.brand.ApiResponse>

}




