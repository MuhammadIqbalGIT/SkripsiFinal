package com.example.core.data

import com.example.core.data.remote.response.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class OnlyNetworkBoundResource<ResultType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> emit(Resource.Success(apiResponse.data))
            is ApiResponse.Empty -> emit(Resource.Empty())
            is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            else -> {}
        }
    }

    protected abstract suspend fun createCall(): Flow<ApiResponse<ResultType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}