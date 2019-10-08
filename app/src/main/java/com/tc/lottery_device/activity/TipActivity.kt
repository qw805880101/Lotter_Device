package com.tc.lottery_device.activity

import android.content.Intent
import com.tc.lottery_device.R
import com.tc.lottery_device.base.BaseActivity
import kotlinx.android.synthetic.main.activity_tip.*


/**
 * 购彩提示页面
 */
class TipActivity : BaseActivity() {
    override fun getContentView(): Int {
        return R.layout.activity_tip
    }

    override fun initView() {

        //购买彩票
        txtBuy.setOnClickListener {
            val intent = Intent(this, BuyActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        //返回
        txtBack.setOnClickListener {
            this.finish()
        }

    }

    override fun initData() {

    }
}