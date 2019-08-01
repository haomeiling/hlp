package cn.bxd.sip.bxd.service;

/**
 * 异步业务服务类
 *
 * @author HML
 * @Date 2016/2/1
 */
public interface ISyncEvtService {
    /**
     * 新增通知:通知医院还号
     *
     * @param hosId    医院ID
     * @param queueId  队列编号
     * @param periodNo 时段序号
     * @param orderDay 就诊时间
     * @param regNo    号数
     * @return
     */
    void addNotifyBackNo(int hosId, String queueId, short periodNo, Integer orderDay, String regNo);

    /**
     * 新增通知：取消订单
     *
     * @param orderId 订单编号
     */
    void addNotifyCancelOrder(long orderId);

    /**
     * 新增通知：支付成功
     *
     * @param orderId 订单编号
     * @param transId 交易流水号
     */
    void addNotifyPaySuccess(long orderId, long transId);

    /**
     * 新增通知：退款成功
     *
     * @param orderId 订单编号
     * @param transId 交易流水号
     */
    void addNotifyRefundSuccess(long orderId, long transId);

    /**
     * 新增通知：订单消息
     *
     * @param orderId 订单编号
     */
    void addNotifyOrderConfirm(long orderId);


    /**
     * 获取待处理异步消息ID字符串
     * 调用存储过程
     * @return 消息ID字符串 ，通过逗号分隔
     */
    String getEvtPendingIdStr();

}
