package cn.bxd.sip.bxd.model.dto.pay;

import java.math.BigDecimal;

/**
 * Description: TODO
 * Package: com.bxd.medicalinsurance.model.pay
 *
 * @author Leeves
 * @date 2018-02-05
 */

public class AppPayRes {
    /** 返回状态码 */
    private String returnCode;

    /** 返回信息 */
    private String returnMsg;

    /** 处理结果 */
    private String resultCode;

    /** 结果描述 */
    private String resultMsg;

    /** 错误编码 */
    private String errCode;

    /** 签名*/
    private String sign;

    /**交易流水号ID*/
    private Long transId;

    /**请求编码*/
    private String requestNo;

    /** 交易金额*/
    private BigDecimal amount;

    /**支付渠道*/
    private String providerCode;

    /** 应用ID */
    private String appid;

    /** 商户号*/
    private String partnerid;

    /** 预支付交易会话ID */
    private String prepayid;

    /** 扩展字段 */
    private String packageStr;

    /** 随机字符串 */
    private String noncestr;

    /** 时间戳 */
    private String timestamp;

    /** 第三方签名 */
    private String appSign;

    /** 内容串，业务参数内容串集合 */
    private String content;

    @Override
    public String toString() {
        return "AppPayRes{" +
                "returnCode='" + returnCode + '\'' +
                ", returnMsg='" + returnMsg + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", errCode='" + errCode + '\'' +
                ", sign='" + sign + '\'' +
                ", transId=" + transId +
                ", requestNo='" + requestNo + '\'' +
                ", amount=" + amount +
                ", providerCode='" + providerCode + '\'' +
                ", appid='" + appid + '\'' +
                ", partnerid='" + partnerid + '\'' +
                ", prepayid='" + prepayid + '\'' +
                ", packageStr='" + packageStr + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", appSign='" + appSign + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getTransId() {
        return transId;
    }

    public void setTransId(Long transId) {
        this.transId = transId;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAppSign() {
        return appSign;
    }

    public void setAppSign(String appSign) {
        this.appSign = appSign;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
