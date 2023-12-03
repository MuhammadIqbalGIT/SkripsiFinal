package com.example.core.data.remote.network

import com.example.core.data.local.session.Session
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor @Inject constructor(private val session: Session) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val token: String = session.getTokenNew()

        val lOriginalRequest = chain.request()
        val lRequest = lOriginalRequest.newBuilder().header("Authorization", "Bearer $token")
            .method(lOriginalRequest.method, lOriginalRequest.body).build()

        return if (token.isEmpty()) {
            chain.proceed(lOriginalRequest)
        } else {
            chain.proceed(lRequest)
        }
    }
}