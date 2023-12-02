package com.example.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("expirationDate")
    var expirationDate: String,
    @SerializedName("token")
    var token: String
)