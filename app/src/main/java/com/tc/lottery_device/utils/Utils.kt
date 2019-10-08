package com.tc.lottery_device.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.Nullable
import android.util.DisplayMetrics
import android.view.WindowManager
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializerFeature
import com.example.mylibrary.util.DateUtil
import com.example.mylibrary.util.LogUtil
import com.tc.lottery_device.BuildConfig
import com.tc.lottery_device.LotteryDeviceApplication
import okhttp3.MediaType
import okhttp3.RequestBody
import java.net.SocketException
import java.net.SocketTimeoutException
import java.util.*
import kotlin.collections.HashMap


object Utils {

    private fun isEmpty(@Nullable str: CharSequence?): Boolean {
        return str == null || str.isEmpty()
    }

    fun showToast(context: Context, str: String) {

        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()

    }

    /**
     * 获取屏幕的宽
     *
     * @param context
     * @return
     */
    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        return dm.widthPixels
    }

    /**
     * 获取错误类型提示
     *
     * @param throwable
     * @return
     */
    fun getErrorMessage(throwable: Throwable): String {

        var errorMessage = "网络错误"

        if (throwable is SocketException) { //该异常在客户端和服务器均可能发生。异常的原因是己方主动关闭了连接后（调用了Socket的close方法）再对网络连接进行读写操作。
            errorMessage = "网络已断开，请重试"
        } else if (throwable is SocketTimeoutException) { //网络连接超时
            errorMessage = "网络连接超时,请稍后再试"
        }

        return errorMessage
    }

    /**
     * 获取公共参数map
     *
     * @param application 应用名称
     * @return
     */
    fun getRequestData(application: String): HashMap<String, String> {
        val requestData = HashMap<String, String>()
        requestData["version"] = BuildConfig.VERSION_NAME //接口版本号
        requestData["application"] = application //应用名称
        requestData["sendTime"] = DateUtil.format(Date(), "yyyymmddhhmmss") //发送时间
        requestData["terminalCode"] = "0002" //终端类型
        requestData["terminalId"] = LotteryDeviceApplication.deviceId //终端编号
        return requestData
    }

    fun getRequestBody(requestData: Map<String, String>): RequestBody {

        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), getSendMsg(requestData))
    }

    /**
     * 获取请求json
     *
     * @param requestData
     * @return
     */
    private fun getSendMsg(requestData: Map<String, String>): String {
        try {
            val map = HashMap<String, Any>()
            val sign = SignUtil.sign(
                JSON.toJSONString(
                    requestData,
                    SerializerFeature.DisableCircularReferenceDetect
                ), BuildConfig.PRIVATE_KEY
            )

            LogUtil.d(sign)
            map["sign"] = sign.substring(0, sign.length - 1)
            map["requestData"] = requestData
            return JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    fun isEmpty(str: String): Boolean {
        return str == null || str == ""
    }
}