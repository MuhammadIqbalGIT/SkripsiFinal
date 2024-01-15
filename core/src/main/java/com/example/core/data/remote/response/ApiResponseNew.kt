package com.example.core.data.remote.response

import com.example.core.domain.model.DetailProduct


data class ProductApiResponse(
    val product: DetailProduct
)

data class ApiResponseNew<T>(
    val success: Boolean,
    val message: String,
    val data: T
)