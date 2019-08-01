package cn.bxd.sip.bxd.model.dto.pay;

/**
 * Description: TODO
 * Package: com.bxd.medicalinsurance.model.pay
 *
 * @author Leeves
 * @date 2018-01-16
 */

public class PayResErrorMsg {

    /** 返回状态码 */
    private String returnCode;

    /** 返回信息 */
    private String returnMsg;

    /** 处理结果 */
    private String resultCode;

    /** 错误代码 */
    private String errCode;

    /** 结果描述 */
    private String resultMsg;

    /** 申请识别号 */
    private String requestNo;

    /** 交易流水号 */
    private String transId;

    /** 交易金额 */
    private String amount;

    /** 支付渠道 */
    private String providerCode;

    /** 支付跳转链接 */
    private String mweb_url;

    /** 签名 */
    private String sign;


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

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getMweb_url() {
        return mweb_url;
    }

    public void setMweb_url(String mweb_url) {
        this.mweb_url = mweb_url;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "PayResErrorMsg{" +
                "returnCode='" + returnCode + '\'' +
                ", returnMsg='" + returnMsg + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", errCode='" + errCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", requestNo='" + requestNo + '\'' +
                ", transId='" + transId + '\'' +
                ", amount='" + amount + '\'' +
                ", providerCode='" + providerCode + '\'' +
                ", mweb_url='" + mweb_url + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
