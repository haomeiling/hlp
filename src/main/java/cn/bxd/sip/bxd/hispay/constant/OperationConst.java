package cn.bxd.sip.bxd.hispay.constant;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 15:09
 */
public class OperationConst {
    // 通用应答
    /** 通用消息应答 */
    public final static int OPR_CODE_OF_COMMON_MSG_RES = 929901;
    /** 通用业务应答 */
    public final static int OPR_CODE_OF_COMMON_BUSIN_RES = 929911;
    /** 通用系统应答 */
    public final static int OPR_CODE_OF_COMMON_SYS_RES = 929912;
    /** 系统错误应答 */
    public final static int OPR_CODE_OF_SYS_ERROR_RES = 929999;

    /** 刷卡支付请求 */
    public final static int HOSPITAL_SCAN_PAY_REQ = 210701;
    /** 刷卡支付应答 */
    public final static int HOSPITAL_SCAN_PAY_RES = 120701;

    /** 扫码支付请求 */
    public final static int HOSPITAL_MICRO_PAY_REQ = 210702;
    /** 扫码支付应答 */
    public final static int HOSPITAL_MICRO_PAY_RES = 210702;
    /** 刷卡支付结果查询请求 */
    public final static int HOSPITAL_PAY_QUERY_REQ = 210703;
    /** 刷卡支付结果查询应答 */
    public final static int HOSPITAL_PAY_QUERY_RES = 120703;

    /** 扫码支付退费请求 */
    public final static int HOSPITAL_REFUND_PAY_REQ = 210710;

    /** 扫码支付退费应答 */
    public final static int HOSPITAL_REFUND_PAY_RES = 120710;

    /** 刷卡支付结果查询请求 */
    public final static int HOSPITAL_H5_PAY_REQ = 210704;
    /** 刷卡支付结果查询应答 */
    public final static int HOSPITAL_H5_PAY_RES = 122010;

    //======================我是分割线===================================
    // 费用相关
    /** 缴费通知请求 **/
    public final static int OPR_CODE_OF_PAYMENT_NOTIFY_REQ = 212003;
    /** 缴费通知应答 **/
    public final static int OPR_CODE_OF_PAYMENT_NOTIFY_RES = 122003;
    /** 刷卡缴费通知请求 **/
    public final static int OPR_CODE_OF_MICRO_PAYMENT_NOTIFY_REQ = 212008;
    /** 刷卡缴费通知应答 **/
    public final static int OPR_CODE_OF_MICRO_PAYMENT_NOTIFY_RES = 122008;
    /** 刷卡缴费查询通知请求 **/
    public final static int OPR_CODE_OF_MICRO_PAYMENT_QUERY_REQ = 212009;
    /** 刷卡缴费查询通知应答 **/
    public final static int OPR_CODE_OF_MICRO_PAYMENT_QUERY_RES = 122009;
    /** 扫码缴费通知请求 **/
    public final static int OPR_CODE_OF_H5_PAYMENT_QUERY_REQ = 212010;
    /** 扫码缴费通知应答 **/
    public final static int OPR_CODE_OF_H5_PAYMENT_QUERY_RES = 122010;
    /** 订单退款申请 **/
    public final static int OPR_CODE_OF_ORDER_REFUNDS_APPLY = 202004;
    /** 费用单查询请求 **/
    public final static int OPR_CODE_OF_CLINIC_FEE_REQ = 112006;
    /** 费用单查询应答 **/
    public final static int OPR_CODE_OF_CLINIC_FEE_RES = 222006;
    /** 费用单查明细询请求 **/
    public final static int OPR_CODE_OF_CLINIC_FEE_DETAIL_REQ = 112007;
    /** 费用单查明细询应答 **/
    public final static int OPR_CODE_OF_CLINIC_FEE_DETAIL_RES = 222007;
}
