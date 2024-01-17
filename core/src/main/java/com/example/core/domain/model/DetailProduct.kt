package com.example.core.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ProductApiResponse(
    @SerializedName("product")
    val product: DetailProduct
) : Serializable



data class DetailProduct(

    @SerializedName("id") val id: Int,
    @SerializedName("nama") val nama: String,
    @SerializedName("satuan") val satuan: String,
    @SerializedName("image") val image: String?,
    @SerializedName("stok") val stok: Double,
    @SerializedName("harga") val harga: Double,
    @SerializedName("BrandID") val brandId : Int,
    @SerializedName("NamaBrand") val namaBrand : String
): Serializable
