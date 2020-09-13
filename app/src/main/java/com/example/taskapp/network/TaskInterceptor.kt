package com.example.taskapp.network

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody

class TaskInterceptor(private val jsonString: ByteArray) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(request)
            .newBuilder()
            .body(ResponseBody.create(MediaType.parse("application/json"), jsonString))
            .build()
    }

}