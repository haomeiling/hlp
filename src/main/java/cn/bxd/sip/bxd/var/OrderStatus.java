package cn.bxd.sip.bxd.var;

/**
 * @author:tangliang
 * @date:2018/7/26
 * @description:
 */
//订单状态

public enum OrderStatus {
    PAYING_SELF_FEE("PAYING_SELF_FEE", 1),// 订单全部自费支付中
    PAYING_SOCIAL_SECURITY("PAYING_SOCIAL_SECURITY", 2),//  社保移动支付中
    PAYING_READ_CARD("PAYING_READ_CARD", 3),//  社保移动支付读卡中
    PAYING_READ_DATA ("PAYING_READ_DATA", 4),//  社保移动支付数据读取中
    PAYING_CHECK("PAYING_CHECK", 5),//  社保移动支付门诊/住院登记中
    PAYING_CHECK_REVOKING("PAYING_CHECK_REVOKING", 6),//   社保移动支付门诊/住院登记撤销中
    PAYING_DETAIL_UPLOADING("PAYING_DETAIL_UPLOADING", 7),//  社保移动支付明细上传中
    PAYING_DETAIL_UPLOAD_REVOKING ("PAYING_DETAIL_UPLOAD_REVOKING", 8),//   社保移动支付明细上传撤销中
    PAYING_BUDGET ("PAYING_BUDGET", 9),//  社保移动支付预结算中
    PAY_BUDGET_SUCCESS ("PAY_BUDGET_SUCCESS", 10),//  社保移动支付预结算成功
    PAY_BUDGET_FAILS ("PAY_BUDGET_FAILS", 11),//  社保移动支付预结算失败
    PAYING_SETTLE_RTS ("PAYING_SETTLE_RTS", 12),//  社保移动支付结算请求中
    PAYING_SETTLEING ("PAYING_SETTLEING", 13),//  社保移动支付结算中
    PAY_SETTLE_SUCCESS ("PAY_SETTLE_SUCCESS", 14),//  社保移动支付结算成功
    PAY_SETTLE_FAILS ("PAY_SETTLE_FAILS", 15),//  社保移动支付结算失败

    PAY_SOCIAL_SECURITY_SUCCESS  ("PAY_SOCIAL_SECURITY_SUCCESS", 16),// 社保移动支付成功
    PAY_SOCIAL_SECURITY_FAILS ("PAY_SOCIAL_SECURITY_FAILS", 17),//  社保移动支付失败

    PAYING_PART_SELF_FEE ("PAYING_PART_SELF_FEE", 18),//  订单部分自费支付中
    PAY_SELF_FEE_SUCCESS ("PAY_SELF_FEE_SUCCESS", 19),//  自费支付成功
    PAY_SELF_FEE_FAILS  ("PAY_SELF_FEE_FAILS", 20),//  自费支付失败

    PLATFORM_PAYMENT_HANDLING("PLATFORM_PAYMENT_HANDLING", 21),//   缴费单位缴费处理中
    PLATFORM_PAYMENT_SUCCESS ("PLATFORM_PAYMENT_SUCCESS", 22),//  缴费单位金额到账成功
    PLATFORM_PAYMENT_FAILS ("PLATFORM_PAYMENT_FAILS", 23),//  缴费单位金额到账失败

    HIS_REFUND_HANDLING("HIS_REFUND_HANDLING", 24),//  缴费单位退款处理中
    PLATFORM_REFUND_HANDLING("PLATFORM_REFUND_HANDLING", 25),//  平台业务退款处理中

    REFUND_SELF_HANDLING ("REFUND_SELF_HANDLING", 26),//  订单自费退款处理中
    REFUND_SELF_SUCCESS  ("REFUND_SELF_SUCCESS", 27),// 订单自费退款成功
    REFUND_SELF_ERROR  ("REFUND_SELF_ERROR", 28),// 订单退费失败

    REFUND_SOCIAL_SECURITY_HANDLING("REFUND_SOCIAL_SECURITY_HANDLING", 29),//   社保移动支付退款处理中
    REFUND_SOCIAL_SECURITY_SUCCESS ("REFUND_SOCIAL_SECURITY_SUCCESS", 30),//  社保移动支付退款成功
    REFUND_SOCIAL_SECURITY_ERROR ("REFUND_SOCIAL_SECURITY_ERROR", 31),//  社保移动支付退费失败

    REFUND_SUCCESS ("REFUND_SUCCESS", 32),//  订单退款成功
    REFUND_ERROR  ("REFUND_ERROR", 33),// 订单退款失败

    CANCEL_SUCCESS  ("CANCEL_SUCCESS", 34),//  订单取消成功   Lisheng    2018/11/14
    RETURN_SUCCESS  ("RETURN_SUCCESS", 35),//  订单还号成功   Lisheng    2018/11/14
    PAY_SUCCESS  ("PAY_SUCCESS", 36);//  订单支付完成        Lisheng    2018/11/14


    // 成员变量
    private String name;
    private int id;

    // 构造方法
    private OrderStatus(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // 普通方法
    public static String getName(int id) {
        for (OrderStatus c : OrderStatus.values()) {
            if (c.getId() == id) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

