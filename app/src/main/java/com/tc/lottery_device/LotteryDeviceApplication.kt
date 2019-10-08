package com.tc.lottery_device

import android.app.Application
import cn.jpush.android.api.JPushInterface
import com.example.mylibrary.base.BaseApplication

class LotteryDeviceApplication  : Application() {

    companion object{
        var deviceId = ""
        var JPushId = ""
    }

    override fun onCreate() {
        super.onCreate()
        registerJPush()
        BaseApplication.init(this)
    }

    private fun registerJPush() {
        //设置调试模式
        JPushInterface.setDebugMode(BuildConfig.IS_TEST_URL)

        //初始化极光推送
        JPushInterface.init(this)

        JPushId = JPushInterface.getRegistrationID(this)
    }
}