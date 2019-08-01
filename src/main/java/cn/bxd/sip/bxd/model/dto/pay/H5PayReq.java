package cn.bxd.sip.bxd.model.dto.pay;

/**
 * Description: TODO: H5支付请求参数类
 * Package: com.bxd.medicalinsurance.model.pay
 *
 * @author Leeves
 * @date 2018-01-11
 */

public class H5PayReq {

    /** 支付渠道 */
    private String providerCode;

    /** 支付来源 */
    private String sourceCode;

    /** 交易金额 */
    private String amount;

    /** 申请识别号, */
    private String requestNo;

    /** 第三方用户id */
    private String openId;

    /** 返回地址 */
    private String location;

    /** 商品描述 */
    private String body;

    /** 令牌标识 */
    private String token;

    /** 授权因子 */
    private String authCode;

    /** 签名 */
    private String sign;

    @Override
    public String toString() {
        return "H5PayReq{" +
                "providerCode='" + providerCode + '\'' +
                ", sourceCode='" + sourceCode + '\'' +
                ", amount='" + amount + '\'' +
                ", requestNo='" + requestNo + '\'' +
                ", openId='" + openId + '\'' +
                ", location='" + location + '\'' +
                ", body='" + body + '\'' +
                ", token='" + token + '\'' +
                ", authCode='" + authCode + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
