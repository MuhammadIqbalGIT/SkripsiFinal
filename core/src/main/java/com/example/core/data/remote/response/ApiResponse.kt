package com.example.core.data.remote.response

sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
    data class ErrorValue<out T>(val errorMessage: String, val data: T?) : ApiResponse<T>()
    data class ErrorSystem(val errorMessage: String) : ApiResponse<Nothing>()
    data class ErrorAPI(val errorMessage: String) : ApiResponse<Nothing>()
    data class ErrorConnection(val errorMessage: String) : ApiResponse<Nothing>()
    object ErrorNoInternetConnection : ApiResponse<Nothing>()
    object ErrorServerMaintenance : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}