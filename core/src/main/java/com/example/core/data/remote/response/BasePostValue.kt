package com.example.core.data.remote.response

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class BasePost(
    @SerializedName("Token")
    var token: String,
    @SerializedName("UserCode")
    var userCode: String,
    @SerializedName("Origins")
    var origin: Origin,
)

data class BasePostValue<TValue>(
    @SerializedName("Token")
    var token: String,
    @SerializedName("UserCode")
    var userCode: String,
    @SerializedName("Origins")
    var origin: Origin,
    @SerializedName("Value")
    var value: TValue
)

data class BasePostValue2<TValue>(
    @SerializedName("Token")
    var token: String,
    @SerializedName("UserCode")
    var userCode: String,
    @SerializedName("Origins")
    var origin: Origin
) {


    fun toJson(): String = Gson().toJson(this)
}


data class Origin(
    @SerializedName("Source")
    var source: String,
    @SerializedName("Version")
    var version: String
)