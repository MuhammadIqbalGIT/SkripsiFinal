package com.example.core.data.remote.network

import com.example.core.data.local.session.Session
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor @Inject constructor(private val session: Session) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {


        //perubahan (perlu tidak perlu token/di project sudah real butuh token)
        val token: String = "eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiYWNjZXNzIiwianRpIjoiM2Q3NTg4YTAtYzU0My00MTAwLTg2MmQtMTBhZjk2MThmZDFkIiwiZXhwIjoxNzAzMzQzMTAwLCJpc3MiOiJhY2Nlc3NJc3N1ZXIiLCJhdWQiOiJhY2Nlc3NBdWRpZW5jZSJ9.6h66s8nIBWPmkW8iqAmQ6oomAFPD9ledVvdVfblqKKo"

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