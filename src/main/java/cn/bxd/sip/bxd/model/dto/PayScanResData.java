package cn.bxd.sip.bxd.model.dto;

import java.math.BigDecimal;

public class PayScanResData {
    /*返回状态码*/
    private String returnCode;
    /* 返回信息*/
    private String returnMsg;
    /*处理结果*/
    private String resultCode;
    /* 结果描述*/
    private String resultMsg;
    /*错误编码*/
    private String errCode;
   /* 申请识别码*/
    private String requestNo;
    /*交易流水号*/
    private Long transId;
    /*交易金额*/
    private BigDecimal amount;
    /*交易渠道*/
    private Byte providerId;
    /*二维码地址*/
    private String codeURL;
   /* 签名*/
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

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public long getTransId() {
        return transId;
    }

    public void setTransId(long transId) {
        this.transId = transId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Byte getProviderId() {
        return providerId;
    }

    public void setProviderId(Byte providerId) {
        this.providerId = providerId;
    }

    public String getCodeURL() {
        return codeURL;
    }

    public void setCodeURL(String codeURL) {
        this.codeURL = codeURL;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
