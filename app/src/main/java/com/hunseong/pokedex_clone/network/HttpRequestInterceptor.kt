package com.hunseong.pokedex_clone.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber


// Interceptor를 통해 API 통신을 모니터링, connection timeout 설정 등의 역할 가능
class HttpRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().url(originalRequest.url).build()
        Timber.d(request.toString()) // request log
        return chain.proceed(request) // request에 대한 response return
    }
}