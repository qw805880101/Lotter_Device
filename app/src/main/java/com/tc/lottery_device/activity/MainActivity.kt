package com.tc.lottery_device.activity

import android.content.Intent
import android.view.View
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator
import com.example.mylibrary.net.RxUtil
import com.tc.lottery_device.R
import com.tc.lottery_device.base.BaseActivity
import com.tc.lottery_device.utils.LocalImageViewBanner
import com.tc.lottery_device.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * 待机界面
 */
class MainActivity : BaseActivity() {

    private val bannerImages = ArrayList<String>()

    private lateinit var banner: ConvenientBanner<String>

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

        banner = findViewById(R.id.bannerTop)

        //购买按钮
        btBuy.setOnClickListener {
            val intent = Intent(this, BuyActivity::class.java)
            startActivity(intent)
        }

        //如何兑换按钮
        btPrompt.setOnClickListener {
            val intent = Intent(this, TipActivity::class.java)
            startActivity(intent)
        }

//        val counts = 5//点击次数
//        val duration = (3 * 1000).toLong()//规定有效时间
//        var mHits = LongArray(counts)
//        //底部左侧图片按钮
//        ivSet.setOnClickListener {
//            /**
//             * 实现双击方法
//             * src 拷贝的源数组
//             * srcPos 从源数组的那个位置开始拷贝.
//             * dst 目标数组
//             * dstPos 从目标数组的那个位子开始写数据
//             * length 拷贝的元素的个数
//             */
//            System.arraycopy(mHits, 1, mHits, 0, mHits.size - 1)
//            //实现左移，然后最后一个位置更新距离开机的时间，如果最后一个时间和最开始时间小于DURATION，即连续5次点击
//            mHits[mHits.size - 1] = SystemClock.uptimeMillis()
//            if (mHits[0] >= SystemClock.uptimeMillis() - duration) {
//                //                String tips = "您已在[" + DURATION + "]ms内连续点击【" + mHits.length + "】次了！！！";
//                //                ToastUtils.showToast(MainActivity.this, tips);
//                val intent = Intent(this, SetActivity::class.java)
//                startActivity(intent)
//            }
//        }

        initBanner()

    }

    override fun initData() {

        startBanner()
    }

    /**
     * 初始化接口
     */
    private fun initApi() {
        val sendMap = Utils.getRequestData("terminalInit.Req")
        val requestBody = Utils.getRequestBody(sendMap)

        val init = lotteryApi.init(requestBody).compose(RxUtil.rxSchedulerHelper())

        rxManager.add(init.subscribe({
            
        }, {

        }))

    }

    /**
     * 初始化首页广告
     */
    private fun initBanner() {
        banner.setPages(object : CBViewHolderCreator {
            override fun createHolder(itemView: View): LocalImageViewBanner {
                return LocalImageViewBanner(this@MainActivity, itemView)
            }

            override fun getLayoutId(): Int {
                return R.layout.view_home_banner
            }
        }, bannerImages)

        banner.setOnItemClickListener {
            //            Utils.showToast(this.context!!, "点击了第${it}页")
        }
    }

    private fun startBanner() {
        banner.notifyDataSetChanged()
//        banner.setPageIndicator(
//            intArrayOf(
//                R.mipmap.icon_goods_banner_no_selector,
//                R.mipmap.icon_goods_banner_selector
//            )
//        )
        banner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
    }

    override fun onResume() {
        super.onResume()
        banner.startTurning(4500)
    }

    override fun onStop() {
        super.onStop()
        banner.stopTurning()
    }

}