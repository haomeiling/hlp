package cn.bxd.sip.bxd.model.dto;

/**
 * @author：haomeiling
 * @Description：交易取消出参
 * @Date：2017年3月1日 下午4:01:19
 */
public class PayCancelResData {
    /*返回状态码*/
    private String returnCode;
    /* 返回信息*/
    private String returnMsg;
    /*处理结果*/
    private String resultCode;
    /*错误编码*/
    private String errCode;
    /*结果描述*/
    private String resultMsg;
    /*申请识别码*/
    private String requestNo;
    /*签名*/
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
