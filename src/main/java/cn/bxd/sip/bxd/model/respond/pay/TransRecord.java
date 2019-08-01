package cn.bxd.sip.bxd.model.respond.pay;

/**
 * Description:
 * User: LiSheng
 * Date: 2019-04-12
 * Time: 10:53
 */
public class TransRecord {

    private String transId;//交易平台唯一标识
    private String hisOrderNo;//	医院订单号
    private String orderNo;//	平台订单号
    private String transTime;//	交易时间，格式：YYYYMMDD HHMMSS
    private String amount;//	交易金额，单位元；如果是退款记录，则金额为负数：-2.00
    private String transMessage;//	交易结果说明。如果是退款记录，则为退款标志+流水号：REFUNDTRANSID:2018030921001004950585117900
    private String providerCode;//	1- 微信支付,2- 支付宝支付
    private String orderType;//	1-  预约挂号,2-  门诊交费,3-  住院缴费,4-  改签订单,5-  取消订单,6-  签到订单,7-  当日挂号
    private String transScene;//	1- 支付宝生活号/微信公众号支付,2- 扫码支付,3- 刷卡支付,4- 网页支付,5- 终端支付,6- 云pos支付
    private String hospitalCode;//	机构代号
    private String transactionId;//	第三方交易成功流水号
    private String transStatus;//	交易状态SUCCESS/REFUND

}
