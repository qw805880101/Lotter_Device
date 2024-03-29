package com.example.mylibrary.net

import com.example.mylibrary.base.BaseApplication
import com.example.mylibrary.util.NetUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        if (!NetUtils.isConnected(BaseApplication.mContext!!)) {
            request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
        }
        val response = chain.proceed(request)
        if (NetUtils.isConnected(BaseApplication.mContext!!)) {
            val maxAge = 0
            // 有网络时, 不缓存, 最大保存时长为0
            response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("Pragma")
                .build()
        } else {
            // 无网络时，设置超时为4周
            val maxStale = 60 * 60 * 24 * 28
            response.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }
        return response
    }
}