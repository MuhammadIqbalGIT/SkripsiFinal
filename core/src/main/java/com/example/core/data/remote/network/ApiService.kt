package com.example.core.data.remote.network

import com.example.core.data.remote.request.LoginRequest
import com.example.core.data.remote.response.BasePost
import com.example.core.data.remote.response.BaseResponseList
import com.example.core.data.remote.response.BaseResponseListV3
import com.example.core.data.remote.response.BaseResponseValue
import com.example.core.domain.model.PenggunaModel
import com.example.core.domain.model.employee.EmployeeModel
import com.example.core.domain.model.login.LoginModel
import com.example.core.domain.model.product.ProductModel
import com.example.core.domain.model.staff.ListStaffModel
import com.example.core.domain.model.staff.StaffModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json",
        "Connection: keep-alive"
    )
    @GET("api/Employee/GetEmployee")
    suspend fun getEmployee(
        @Query("employeeId") employeeId: Int,
    ): Response<BaseResponseList<EmployeeModel>>

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json",
        "Connection: keep-alive"
    )
    @POST("GetAllEmployee")
    suspend fun getAllEmployee(@Body basePost: BasePost): Response<BaseResponseList<EmployeeModel>>

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json",
        "Connection: keep-alive"
    )
    @GET("server_api/index.php/ServerApi/getDataStaff")
    suspend fun getDataStaff(): Response<BaseResponseValue<StaffModel>>

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json",
        "Connection: keep-alive"
    )
    @GET("http://10.0.2.2:8080/server_api/index.php/ServerApi/getDataStaff")
    suspend fun getListDataStaff(): Response<BaseResponseList<ListStaffModel>>


    @GET("pengguna")
    suspend fun getPengguna(): Response<BaseResponseListV3<PenggunaModel>>

    //user
    @GET("pengguna")
    fun getPenggunaCall(): Call<ArrayList<PenggunaModel>>

    //Login
    @POST("pengguna/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginModel>


    //Product
    @GET("product")
    fun getProduct(): Call<ArrayList<ProductModel>>


}
