package com.example.mylibrary.net

import com.example.mylibrary.base.BaseApplication
import okhttp3.Cache
import java.io.File

class HttpCache {

    companion object {
        private const val HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 50 * 1024 * 1024

        fun getCache(): Cache {
            return Cache(
                File(BaseApplication.mContext!!.cacheDir.absolutePath + File.separator + "data/NetCache"),
                HTTP_RESPONSE_DISK_CACHE_MAX_SIZE.toLong()
            )
        }

    }
}