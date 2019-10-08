package com.tc.lottery_device.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import cn.jpush.android.api.JPushInterface
import com.example.mylibrary.util.LogUtil

class JPushReceiver : BroadcastReceiver() {
    private val TAG = "JPushReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        try {

            val bundle = intent.extras
            LogUtil.d(TAG, "action:${intent.action}, bundle:$bundle")
            when {
                //根据Registration ID 进行推送 此方法用于处理该类推送消息
                JPushInterface.ACTION_REGISTRATION_ID == intent.action -> {
                    var regId = bundle?.getString(JPushInterface.EXTRA_REGISTRATION_ID)
                    LogUtil.d(TAG, "接收Registration Id : $regId")
                }

                //处理自定义消息
                JPushInterface.ACTION_MESSAGE_RECEIVED == intent.action -> {
                    var jPushContent = bundle?.getString(JPushInterface.EXTRA_MESSAGE)
                    LogUtil.d(TAG, "接收到的消息 : $jPushContent")
                }

                //接收到通知会走的方法
                JPushInterface.ACTION_NOTIFICATION_RECEIVED == intent.action -> {
                    val notifactionId = bundle?.getInt(JPushInterface.EXTRA_NOTIFICATION_ID)
                    var jPushContent = bundle?.getString(JPushInterface.EXTRA_MESSAGE)
                    LogUtil.d(TAG, "接收到推送下来的通知的ID$notifactionId")
//                    val intent = Intent(context, TipActivity::class.java)
//                    context.startActivity(intent)
                }

                //用户点击通知会走的方法(基本不会使用)
                JPushInterface.ACTION_NOTIFICATION_OPENED == intent.action -> {

                }
            }

        } catch (e: Exception) {

        }
    }
}