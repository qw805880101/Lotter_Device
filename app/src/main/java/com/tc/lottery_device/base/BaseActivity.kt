package com.tc.lottery_device.base

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mylibrary.RxManager
import com.example.mylibrary.net.RxService
import com.tc.lottery_device.BuildConfig
import com.tc.lottery_device.api.LotteryApi

abstract class BaseActivity : AppCompatActivity() {

    val rxManager = RxManager()

    val lotteryApi = RxService.createApi(
        LotteryApi::class.java,
        BuildConfig.URL
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())

        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O && Build.VERSION.SDK_INT != Build.VERSION_CODES.O_MR1) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT//设置竖屏模式
        }
        initView()
        initData()
    }

    abstract fun getContentView(): Int

    abstract fun initView()

    abstract fun initData()
}