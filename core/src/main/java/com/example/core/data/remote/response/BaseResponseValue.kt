package com.example.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponseValue<T>(
    @SerializedName("errorCode")
    var errorCode: Int,
    @SerializedName("errorStatus")
    val errorStatus : Boolean,
    @SerializedName("errorMessage")
    var errorMessage: String,
    @SerializedName("value")
    var value: T?
)

data class BaseResponseValue2<T, U>(
    @SerializedName("error")
    var error: Boolean,
    @SerializedName("message")
    var message: String,
    @SerializedName("value1")
    var value1: T,
    @SerializedName("value2")
    var value2: U
)