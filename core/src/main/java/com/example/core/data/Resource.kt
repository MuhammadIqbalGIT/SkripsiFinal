package com.example.core.data

sealed class Resource<T>(val data: T? = null, val message: String = "") {
    class Loading<T> : Resource<T>()
    class Empty<T> : Resource<T>()
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Standby<T>(data: T? = null) : Resource<T>(data)
    class ConnectionError<T> : Resource<T>()
}