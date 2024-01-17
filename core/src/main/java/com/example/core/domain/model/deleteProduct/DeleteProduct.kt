package com.example.core.domain.model.deleteProduct

import java.io.Serializable

data class ApiResponse(
    val success: Boolean,
    val message: String,
    val data: DeleteData?
) : Serializable

data class DeleteData(
    val product: ProductDetails?
) : Serializable

data class ProductDetails(
    val id: String,
    val nama: String,
    val satuan: String,
    val image: String,
    val stok: String,
    val harga: String,
    val brandID: String?
) : Serializable

