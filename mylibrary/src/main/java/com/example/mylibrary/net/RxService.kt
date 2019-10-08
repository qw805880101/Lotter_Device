package com.example.mylibrary.net

import com.example.mylibrary.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class RxService {

    companion object {
        private const val TIMEOUT_READ = 6000
        private const val TIMEOUT_CONNECTION = 6000
        private var headMap: Map<String, String>? = null
        private val cacheInterceptor = CacheInterceptor()
        private val builder = OkHttpClient().newBuilder()
        private var isPrintLog = BuildConfig.DEBUG //是否打印日志

        private val okHttpClient = builder
            //SSL证书
            .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
            //打印日志
            .addInterceptor(getLoggingInterceptor())
            //设置Cache
            .addNetworkInterceptor(cacheInterceptor)//缓存方面需要加入这个拦截器
            .addInterceptor(cacheInterceptor)
            .addNetworkInterceptor(RequestInterceptor())
            .cache(HttpCache.getCache())
            //time out
            .connectTimeout(TIMEOUT_CONNECTION.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_READ.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_READ.toLong(), TimeUnit.SECONDS)
            //失败重连
            .retryOnConnectionFailure(true)
            .build()

        /**
         * 设置报文头
         */
        fun setHeaders(headMap: Map<String, String>) {
            this.headMap = headMap
        }

        /**
         * 设置是否打印日志  默认打印日志
         */
        fun isPrintLog(isPrintLog: Boolean) {
            this.isPrintLog = isPrintLog
        }

        /**
         * 日志打印
         */
        private fun getLoggingInterceptor(): HttpLoggingInterceptor {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            if (isPrintLog) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }
            return httpLoggingInterceptor
        }

        /**
         * 创建Api接口
         */
        fun <T> createApi(clazz: Class<T>, url: String): T {
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(clazz)
        }
    }

    /**
     * 请求拦截器，修改请求header
     */
    class RequestInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val mBuild = original.newBuilder()
            if (headMap != null) {
                for ((key, value) in headMap!!) {
                    mBuild.header(key, value)
                }
            }
            val request = mBuild.build()
//            LogUtil.d("request:" + request.toString())
//            LogUtil.d("request headers:" + request.headers().toString())
            return chain.proceed(request)
        }
    }

}