package com.tc.lottery_device.model

class TerminalLotteryInfo {

    /**
     * 票箱ID（列表）
     */
    private var boxId: String? = null
    /**
     * 彩票ID（列表）
     */
    private var lotteryId: String? = null
    /**
     * 彩票名称（列表）
     */
    private var lotteryName: String? = null
    /**
     * 图片地址（列表）
     */
    private var lotteryImg: String? = null
    /**
     * 彩票单价（列表）
     */
    private var lotteryAmt: String? = null
    /**
     * 剩余（列表）
     */
    private var surplus: String? = null
    /**
     * 票箱状态(列表)
     * 1 正常
     * 2 无票
     * 3 故障
     */
    private var boxStatus: String? = null

    /**
     * 出票状态
     * 1 出票成功
     * 2 出票异常
     */
    private var ticketStatus: String? = null

    /**
     * 张数
     */
    private var num: String? = null
    /**
     * 票尺寸
     */
    private var ticketLen: String? = null

    /**
     * 必传，以实际出票情况送，出成功就传1张数，出票异常则传0张数
     */
    private var ticketNum: String? = null

}