package com.tc.lottery_device.activity

import com.tc.lottery_device.R
import com.tc.lottery_device.base.BaseActivity
import com.tc.lottery_device.utils.Utils
import kotlinx.android.synthetic.main.activity_buy.*


/**
 * 购票界面
 */
class BuyActivity : BaseActivity() {

    //彩票剩余数量
    private var lotterySurplusNum = 0

    //彩票购买数量
    private var lotteryBuyQuantity = 1

    //彩票总金额
    private var lotteryTotalAmount = 0


    override fun getContentView(): Int {
        return R.layout.activity_buy
    }

    override fun initView() {

        //购买数量-1
        btReduce.setOnClickListener {
            buildLotteryQuantity(-1)
        }

        //购买数量+1
        btAdd.setOnClickListener {
            buildLotteryQuantity(1)
        }

        //购买数量+5
        btAddFive.setOnClickListener {
            buildLotteryQuantity(5)
        }

        //购买数量+10
        btAddTen.setOnClickListener {
            buildLotteryQuantity(10)
        }

        //微信下单
        ivWxPay.setOnClickListener {
            buildOrder("02")
        }

        //支付宝下单
        ivAliPay.setOnClickListener {
            buildOrder("01")

        }

    }

    override fun initData() {
    }

    /**
     * 刷新购买彩票数量
     * @quantity 每次更新数量
     */
    private fun buildLotteryQuantity(quantity: Int) {

        if (lotterySurplusNum < lotteryBuyQuantity + quantity) {
            Utils.showToast(this, "当前余票不足")
            return
        }

        if (lotteryBuyQuantity >= 10) {
            Utils.showToast(this, "一次最多购买10张")
            return
        }

        if (lotteryBuyQuantity > 2) {
            lotteryBuyQuantity + quantity
        }

        if (lotteryBuyQuantity > 10) {
            lotteryBuyQuantity = 10
        }

        btReduce.isEnabled = lotteryBuyQuantity != 1
    }

    /**
     * 创建订单
     * @param payType 01 支付宝  02 微信
     */
    private fun buildOrder(payType: String) {


    }

}