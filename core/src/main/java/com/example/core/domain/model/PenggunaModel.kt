package com.example.core.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PenggunaModel (
    @SerializedName("username")
    val username : String,
    @SerializedName("nama")
    val nama : String,
    @SerializedName("level")
    val level: String,
    @SerializedName("password")
    val password : String
) : Serializable