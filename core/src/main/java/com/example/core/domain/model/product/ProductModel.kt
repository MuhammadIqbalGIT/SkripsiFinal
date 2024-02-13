package com.example.core.domain.model.product

import android.widget.CheckBox
import android.widget.EditText
import androidx.resourceinspection.annotation.Attribute.IntMap
import com.google.gson.annotations.SerializedName
import java.io.Serializable




data class  ApiResponseProduct(
    val success: Boolean?,
    val message: String?,
    val data: Data?
)

data class Data(
    var products: List<ProductModel>?
) : Serializable





data class ProductModel(
    @SerializedName("id") val id: Int?=null,
    @SerializedName("nama") val nama: String?=null,
    @SerializedName("satuan") val satuan: String?=null,
    @SerializedName("image") val image: String?=null, // Menggunakan default value untuk image
    @SerializedName("stok") val stok: Double?=null,
    @SerializedName("harga") val harga: Double?=null,
    @SerializedName("BrandID") val brandId: Int?=null,
    @SerializedName("NamaBrand") val namaBrand: String?=null,
    @Transient var editText: EditText? = null, // Menggunakan default value null
    @Transient var isChecked: Boolean = false, // Menggunakan default value false
    @Transient var checkbox: CheckBox? = null // Menggunakan default value null
) : Serializable

