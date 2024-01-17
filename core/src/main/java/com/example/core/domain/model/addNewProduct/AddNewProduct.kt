package com.example.core.domain.model.addNewProduct

import com.example.core.domain.model.product.ProductModel

data class ProductResponse(
    val success: Boolean,
    val message: String,
    val data: ProductData?
)


data class ProductData(
    val product: ProductModel?
)