package com.example.core.domain.model.brand

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BrandModel(
    val BrandID: String,
    val NamaBrand: String,
    val AlamatBrand: String
)

data class  ApiResponse(
    val success: Boolean?,
    val message: String?,
    val data: Data?
)

data class Data(
    val brands: List<BrandModel>?
)
