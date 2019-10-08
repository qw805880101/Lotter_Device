package com.tc.lottery_device.api

import com.tc.lottery_device.model.BaseBean
import com.tc.lottery_device.model.BaseBeanInfo
import com.tc.lottery_device.model.InitInfo
import com.tc.lottery_device.model.OrderInfo
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
interface LotteryApi {

    @POST(".")
    fun init(@Body file: RequestBody): Observable<InitInfo>

    /**
     * 退出
     * @param file
     * @return
     */
    @POST(".")
    fun exit(@Body file: RequestBody): Observable<BaseBeanInfo>

    /**
     * 下单接口
     *
     * @param file
     * @return
     */
    @POST(".")
    fun prepOrder(@Body file: RequestBody): Observable<OrderInfo>

    /**
     * 订单查询
     *
     * @param file
     * @return
     */
    @POST(".")
    fun queryOrder(@Body file: RequestBody): Observable<OrderInfo>

    /**
     * 出票状态更新
     *
     * @param file
     * @return
     */
    @POST(".")
    fun outTicket(@Body file: RequestBody): Observable<InitInfo>

    /**
     * 出票状态更新
     *
     * @param file
     * @return
     */
    @POST(".")
    fun newOutTicket(@Body file: RequestBody): Observable<InitInfo>

    /**
     * 终端状态同步
     *
     * @param file
     * @return
     */
    @POST(".")
    fun terminalUpdate(@Body file: RequestBody): Observable<BaseBean>


}