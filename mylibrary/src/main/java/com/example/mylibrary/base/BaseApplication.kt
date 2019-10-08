package com.example.mylibrary.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import cat.ereza.customactivityoncrash.CustomActivityOnCrash
import cat.ereza.customactivityoncrash.activity.DefaultErrorActivity
import cat.ereza.customactivityoncrash.config.CaocConfig
import org.jetbrains.annotations.NotNull

class BaseApplication {

    private constructor()

    companion object {

        var mContext: Context? = null

        var errConfig = CaocConfig.Builder.create()

        val instance: BaseApplication by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            BaseApplication()
        }

        @SuppressLint("RestrictedApi")
        fun init(mContext: Context) {
            this.mContext = mContext
            errConfig
//                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
//                .enabled(false) //default: true
//                .showErrorDetails(false) //default: true
//                .showRestartButton(false) //default: true
//                .logErrorOnRestart(false) //default: true
//                .trackActivities(true) //default: false
//                .minTimeBetweenCrashesMs(2000) //default: 3000
//                .errorDrawable(R.mipmap.ic_launcher) //default: bug image
//                .restartActivity(LoginActivity::class.java) //default: null (your app's launch activity)
                .errorActivity(DefaultErrorActivity::class.java) //default: null (default error activity)
//                .eventListener(object : CustomActivityOnCrash.EventListener {
//                    override fun onRestartAppFromErrorActivity() {
//                        Log.d("BaseApplication", "onRestartAppFromErrorActivity")
//                    }
//
//                    override fun onCloseAppFromErrorActivity() {
//                        Log.d("BaseApplication", "onCloseAppFromErrorActivity")
//                    }
//
//                    override fun onLaunchErrorActivity() {
//                        Log.d("BaseApplication", "onLaunchErrorActivity")
//                    }
//
//                }) //default: null
                .apply()

            //如果没有任何配置，程序崩溃显示的是默认的设置
            CustomActivityOnCrash.install(mContext)
        }

        /**
         * 设置自定义错误界面
         */
        fun setErrorActivity(@NotNull errorActivity: Class<out Activity>) {
            errConfig.errorActivity(errorActivity)
                .apply()
        }
    }
}