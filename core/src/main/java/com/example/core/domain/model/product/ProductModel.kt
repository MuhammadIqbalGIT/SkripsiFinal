package com.example.core.domain.model.product

import androidx.resourceinspection.annotation.Attribute.IntMap
import com.google.gson.annotations.SerializedName

data class ProductModel(

    @SerializedName("id") val id: Int,
    @SerializedName("nama") val nama: String,
    @SerializedName("satuan") val satuan: String,
    @SerializedName("image") val image: String?,
    @SerializedName("stok") val stok: Double,
    @SerializedName("harga") val harga: Double,
    @SerializedName("BrandID") val brandId : Int,
    @SerializedName("NamaBrand") val namaBrand : String
)
