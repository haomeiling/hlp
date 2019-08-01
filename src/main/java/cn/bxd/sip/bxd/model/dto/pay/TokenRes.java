package cn.bxd.sip.bxd.model.dto.pay;

/**
 * Description: TODO：业务类，请求token返回类
 * Package: com.bxd.medicalinsurance.model.pay
 *
 * @author Leeves
 * @date 2018-01-11
 */

public class TokenRes {

    /** 返回状态码 */
    private String returnCode;

    /** 返回信息 */
    private String returnMsg;

    /** 处理结果 */
    private String resultCode;

    /** 结果描述 */
    private String resultMsg;

    /** 错误代码 */
    private String errCode;

    /** 令牌标识 */
    private String token;

    /** 动态秘钥 */
    private String sessionKey;

    /** 失效时间 */
    private String expiry;

    /** 签名 */
    private String sign;

    @Override
    public String toString() {
        return "TokenRes{" +
                "returnCode='" + returnCode + '\'' +
                ", returnMsg='" + returnMsg + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", errCode='" + errCode + '\'' +
                ", token='" + token + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", expiry='" + expiry + '\'' +
                ", sign='" + sign + '\'' +
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
