package com.example.core.data.remote.datasource

import com.example.core.data.local.session.Session
import com.example.core.data.remote.network.ApiService
import com.example.core.data.remote.response.ApiResponse
import com.example.core.data.remote.response.BasePost
import com.example.core.data.remote.response.BasePostValue
import com.example.core.data.remote.response.Origin
import com.example.core.domain.model.PenggunaModel
import com.example.core.domain.model.employee.EmployeeModel
import com.example.core.domain.model.staff.ListStaffModel
import com.example.core.domain.model.staff.StaffModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class EmployeeDataSource @Inject constructor(private val apiService: ApiService, private val session: Session) : BaseDataSource() {

    suspend fun getEmployee(
        origin: Origin,
        employeeId: Int
    ): Flow<ApiResponse<List<EmployeeModel>>> {
        return flow {
            try {

//                val params = HashMap<String, String>()
//                params["EmployeeId"] = employeeId

                val employeeId = 3

                val response = apiService.getEmployee(employeeId)

                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!
                    if (!body.errorStatus) {
                        emit(ApiResponse.Success(body.value!!))
                    } else {
                        if (body.errorCode == 0) {
                            if (!body.errorMessage.isNullOrEmpty())
                                emit(ApiResponse.Error(body.errorMessage ?: ""))
                            else
                                emit(ApiResponse.ErrorValue(body.errorMessage ?: "", body.value))
                        } else
                            emit(ApiResponse.ErrorAPI(body.errorCode.toString()))
                    }
                } else {
                    emit(ApiResponse.ErrorSystem("$TAG-getEmployee-response-error"))

                }

            } catch (e: Exception) {
                emit(ApiResponse.Error("Terjadi kesalahan"))
            } catch (e: HttpException) {
                emit(
                    ApiResponse.ErrorConnection(
                        e.localizedMessage ?: "An unexpected error occurred"
                    )
                )
            } catch (e: IOException) {
                emit(ApiResponse.ErrorConnection("Tidak Dapat Terhubung. Periksa kembali jaringan anda"))
            }
        }.flowOn(Dispatchers.IO)
    }



    suspend fun getAllEmployee(origin: Origin): Flow<ApiResponse<List<EmployeeModel>>> {
        return flow {
            try {
                val post = BasePost("", "", origin)
                val response = apiService.getAllEmployee(post)
                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!

                    if (!body.errorStatus) {
                        emit(ApiResponse.Success(body.value!!))
                    } else {
                        if (body.errorCode == 0) emit(ApiResponse.Error(body.errorMessage ?: ""))
                        else emit(ApiResponse.ErrorAPI(body.errorCode.toString()))
                    }
                } else {
                    emit(ApiResponse.ErrorSystem("EmployeeDataSource-getAllEmployee-response-error"))
                }
            } catch (e: Exception) {
                emit(ApiResponse.ErrorSystem("Terjadi Kesalahan"))
            } catch (e: HttpException) {
                emit(
                    ApiResponse.ErrorConnection(
                        e.localizedMessage ?: "An unexpected error occurred"
                    )
                )
            } catch (e: IOException) {
                emit(ApiResponse.ErrorConnection("Tidak Dapat Terhubung. Periksa kembali jaringan anda"))
            }
        }.flowOn(Dispatchers.IO)
    }


    suspend fun getListDataStaff(
        origin: Origin,
    ): Flow<ApiResponse<List<ListStaffModel>>> {
        return flow {
            try {


                val response = apiService.getListDataStaff()

                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!

                    if (!body.errorStatus) {
                        emit(ApiResponse.Success(body.value!!))
                    } else {
                        if (body.errorCode == 0)
                            emit(ApiResponse.Error(body.errorMessage ?: ""))
                        else
                            emit(ApiResponse.ErrorAPI(body.errorCode.toString()))
                    }
                } else {
                    emit(ApiResponse.ErrorSystem("EmployeeDataSource-getListDataStaff-response-error"))
                }
            } catch (e: Exception) {
                emit(ApiResponse.ErrorSystem("Terjadi Kesalahan"))
            } catch (e: HttpException) {
                emit(
                    ApiResponse.ErrorConnection(
                        e.localizedMessage ?: "An unexpected error occurred"
                    )
                )
            } catch (e: IOException) {
                emit(ApiResponse.ErrorConnection("Tidak Dapat Terhubung. Periksa kembali jaringan anda"))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDataStaff(
        origin: Origin,
    ): Flow<ApiResponse<StaffModel>> {

        val response = apiService.getDataStaff()

        return flow {
            try {



                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!

                    if (!body.errorStatus) {
                        emit(ApiResponse.Success(body.value!!))
                    } else {
                        if (body.errorCode == 0)
                            emit(ApiResponse.Error(body.errorMessage ?: ""))
                        else
                            emit(ApiResponse.ErrorAPI(body.errorCode.toString()))
                    }
                } else {
                    emit(ApiResponse.ErrorSystem("EmployeeDataSource-getDataStaff-response-error"))
                }
            } catch (e: Exception) {
                emit(ApiResponse.ErrorSystem("Terjadi Kesalahan"))
            } catch (e: HttpException) {
                emit(
                    ApiResponse.ErrorConnection(
                        e.localizedMessage ?: "An unexpected error occurred"
                    )
                )
            } catch (e: IOException) {
                emit(ApiResponse.ErrorConnection("Tidak Dapat Terhubung. Periksa kembali jaringan anda"))
            }
        }.flowOn(Dispatchers.IO)
    }





    suspend fun getPengguna(
        origin: Origin,
    ): Flow<ApiResponse<List<PenggunaModel>>> {
        return flow {
            try {


                val response = apiService.getPengguna()

                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!

                    if (!body.errorStatus) {
                        emit(ApiResponse.Success(body.value!!))
                    } else {
                        if (body.errorCode == 0)
                            emit(ApiResponse.Error(body.errorMessage ?: ""))
                        else
                            emit(ApiResponse.ErrorAPI(body.errorCode.toString()))
                    }
                } else {
                    emit(ApiResponse.ErrorSystem("EmployeeDataSource-getListDataStaff-response-error"))
                }
            } catch (e: Exception) {
                emit(ApiResponse.ErrorSystem("Terjadi Kesalahan"))
            } catch (e: HttpException) {
                emit(
                    ApiResponse.ErrorConnection(
                        e.localizedMessage ?: "An unexpected error occurred"
                    )
                )
            } catch (e: IOException) {
                emit(ApiResponse.ErrorConnection("Tidak Dapat Terhubung. Periksa kembali jaringan anda"))
            }
        }.flowOn(Dispatchers.IO)
    }





}
