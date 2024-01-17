package com.example.core.data.remote.request

data class UpdateProductRequest(
    val id : Int,
    val nama: String,
    val satuan: String,
    val stok: String,
    val harga: String
)