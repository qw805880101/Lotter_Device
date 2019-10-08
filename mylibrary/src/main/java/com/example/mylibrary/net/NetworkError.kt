package com.example.mylibrary.net

import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.text.ParseException
import java.util.concurrent.TimeoutException

class NetworkError {

    companion object {

        private const val UNAUTHORIZED = 401
        private const val FORBIDDEN = 403
        private const val NOT_FOUND = 404
        private const val REQUEST_TIMEOUT = 408
        private const val INTERNAL_SERVER_ERROR = 500
        private const val BAD_GATEWAY = 502
        private const val SERVICE_UNAVAILABLE = 503
        private const val GATEWAY_TIMEOUT = 504

        fun getErrorMessage(throwable: Throwable): ResponseThrowable {

            val mResponseThrowable: ResponseThrowable

            if (throwable is HttpException) {
                mResponseThrowable = ResponseThrowable(throwable, ERROR.HTTP_ERROR)
                when (throwable.code()) {
                    UNAUTHORIZED,
                    FORBIDDEN,
                    NOT_FOUND,
                    REQUEST_TIMEOUT,
                    GATEWAY_TIMEOUT,
                    INTERNAL_SERVER_ERROR,
                    BAD_GATEWAY,
                    SERVICE_UNAVAILABLE -> mResponseThrowable.message = "网络错误"
                    else -> mResponseThrowable.message = "网络错误"
                }
                return mResponseThrowable
            } else if (throwable is TimeoutException || throwable is SocketTimeoutException) {
                mResponseThrowable = ResponseThrowable(throwable, ERROR.TIMEOUT_ERROR)
                mResponseThrowable.message = "网络连接超时"
                return mResponseThrowable
            } else if (throwable is JsonParseException || throwable is JSONException
                || throwable is ParseException || throwable is MalformedJsonException
            ) {
                mResponseThrowable = ResponseThrowable(throwable, ERROR.PARSE_ERROR)
                mResponseThrowable.message = "解析错误"
                return mResponseThrowable
            } else if (throwable is ConnectException) {
                mResponseThrowable = ResponseThrowable(throwable, ERROR.NETWORK_ERROR)
                mResponseThrowable.message = "连接失败,请检查网络是否畅通"
                return mResponseThrowable
            } else if (throwable is javax.net.ssl.SSLHandshakeException) {
                mResponseThrowable = ResponseThrowable(throwable, ERROR.SSL_ERROR)
                mResponseThrowable.message = "证书验证失败"
                return mResponseThrowable
            } else {
                mResponseThrowable = ResponseThrowable(throwable, ERROR.UNKNOWN)
                mResponseThrowable.message = "未知错误"
                return mResponseThrowable
            }
        }
    }

    /**
     * 约定异常
     */
    class ERROR {
        companion object {
            /**
             * 未知错误
             */
            const val UNKNOWN = 1000
            /**
             * 解析错误
             */
            const val PARSE_ERROR = 1001
            /**
             * 网络错误
             */
            const val NETWORK_ERROR = 1002
            /**
             * 协议出错
             */
            const val HTTP_ERROR = 1003
            /**
             * 证书出错
             */
            const val SSL_ERROR = 1004
            /**
             * 连接超时
             */
            const val TIMEOUT_ERROR = 1005
        }
    }

    class ResponseThrowable : Exception {
        var code = -1
        override var message = ""

        constructor(throwable: Throwable, code: Int) : super(throwable) {
            this.code = code
        }

    }

}