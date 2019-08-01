package cn.bxd.sip.bxd.model.dto.pay;


/**
 * Description: TODO: 支付请求后，返回信息类
 * Package: com.bxd.medicalinsurance.model.pay
 *
 * @author Leeves
 * @date 2018-01-12
 */

public class PayReceiveMsg {

    /** 通知类型 */
    private String notifyType;

    /** 申请识别码 */
    private String requestNo;

    /** 交易流水号 */
    private Long transId;

    /** 原交易流水号 */
    private String originalTransId;

    /** 交易记录时间 */
    private String createTime;

    /** 交易发起时间 */
    private String startedTime;

    /** 交易结束时间 */
    private String endedTime;

    /** 交易金额 */
    private String amount;

    /** 交易状态码 */
    private String transCode;

    /** 交易结果说明 */
    private String transMessage;

    /** 交易识别码 */
    private String transactionId;

    /** 随机字符串 */
    private String nonceStr;

    /** 支付方式 ALIPAY WXPAY */
    private String providerTypeCode;

    /** 签名 */
    private String sign;

    public String getProviderTypeCode() {
        return providerTypeCode;
    }

    public void setProviderTypeCode(String providerTypeCode) {
        this.providerTypeCode = providerTypeCode;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public Long getTransId() {
        return transId;
    }

    public void setTransId(Long transId) {
        this.transId = transId;
    }

    public String getOriginalTransId() {
        return originalTransId;
    }

    public void setOriginalTransId(String originalTransId) {
        this.originalTransId = originalTransId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStartedTime() {
        return startedTime;
    }

    public void setStartedTime(String startedTime) {
        this.startedTime = startedTime;
    }

    public String getEndedTime() {
        return endedTime;
    }

    public void setEndedTime(String endedTime) {
        this.endedTime = endedTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getTransMessage() {
        return transMessage;
    }

    public void setTransMessage(String transMessage) {
        this.transMessage = transMessage;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "PayReceiveMsg{" +
                "notifyType='" + notifyType + '\'' +
                ", requestNo='" + requestNo + '\'' +
                ", transId='" + transId + '\'' +
                ", originalTransId='" + originalTransId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", startedTime='" + startedTime + '\'' +
                ", endedTime='" + endedTime + '\'' +
                ", amount='" + amount + '\'' +
                ", transCode='" + transCode + '\'' +
                ", transMessage='" + transMessage + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
