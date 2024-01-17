package com.example.core.data.remote.response


data class ApiResponseNew<T>(
    val success: Boolean,
    val message: String,
    val data: T
)