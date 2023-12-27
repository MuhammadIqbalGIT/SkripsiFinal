package com.example.core.domain.model.login

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: UserData,
    @SerializedName("token") val token: String
)

data class UserData(
    @SerializedName("username") val username: String,
    @SerializedName("nama") val nama: String,
    @SerializedName("level") val level: String,
    @SerializedName("password") val password: String
)
