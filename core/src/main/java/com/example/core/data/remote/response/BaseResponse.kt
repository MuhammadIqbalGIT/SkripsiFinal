package com.example.core.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BaseResponse(
    @SerializedName("errorCode")
    var errorCode: Int,
    @SerializedName("errorStatus")
    var errorStatus: Boolean,
    @SerializedName("errorMessage")
    val errorMessage : String
) : Serializable