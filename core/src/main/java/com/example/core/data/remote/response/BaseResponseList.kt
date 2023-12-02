package com.example.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponseList<T>(
    @SerializedName("errorCode")
    var errorCode: Int,
    @SerializedName("errorStatus")
    var errorStatus: Boolean,
    @SerializedName("errorMessage")
    var errorMessage: String,
    @SerializedName("value")
    var value: List<T>?
)


data class BaseResponseListV2<T>(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("value")
    val value: List<T>? = emptyList(),
)