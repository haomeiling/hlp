package cn.bxd.sip.bxd.var;

/**
 * @author:tangliang
 * @date:2018/8/16
 * @description:
 */
public class OrderType {
    /**
     * 预约订单
     */
    public static final short ORDER_TYPE_APPOINTMENT = 1;
    /**
     * 缴费订单(诊间支付)
     */
    public static final short ORDER_TYPE_OUTPATIENT = 2;
    /**
     * 预交订单(预交金（住院押金支付）)
     */
    public static final short ORDER_TYPE_ADVANCE = 3;
    /**
     * 改签订单
     */
    public static final short ORDER_TYPE_ENDORSE = 4;
    /**
     * 取消订单(该类型为前置机定义的类型，平台暂时不使用该类型)
     */
    public static final short ORDER_TYPE_CANCEL = 5;

    /**
     * 签到订单（预约转挂号）      20171117     新增订单类型
     */
    public static final short ORDER_TYPE_ARRIVAL = 6;

    /**
     * 挂号订单(挂号订单)        20171117     新增订单类型
     */
    public static final short ORDER_TYPE_REGISTRATION = 7;

    /**
     * 停车缴费        20180613     江滨医院提出与停止智能系统对接
     */
    public static final short ORDER_TYPE_PARKING =8;
}
