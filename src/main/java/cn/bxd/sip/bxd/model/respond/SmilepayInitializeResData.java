package cn.bxd.sip.bxd.model.respond;

/**
 * @ author：lisheng
 * @ Description：人脸初始化
 * @ Date：2019年05月16日
 */

public class SmilepayInitializeResData {
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
    /* 签名*/
    private String sign;
    /* 阿里云返回的结果参数 */
    private String aliResult;

    @Override
    public String toString() {
        return "SmilepayInitializeResData{" +
                "returnCode='" + returnCode + '\'' +
                ", returnMsg='" + returnMsg + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", errCode='" + errCode + '\'' +
                ", sign='" + sign + '\'' +
                ", aliResult='" + aliResult + '\'' +
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

    public String getAliResult() {
        return aliResult;
    }

    public void setAliResult(String aliResult) {
        this.aliResult = aliResult;
    }
}
