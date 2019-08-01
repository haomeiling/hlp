package cn.bxd.sip.bxd.model.dto;

import java.math.BigDecimal;

/**
 * 
 * @Description:
 * @author: chenchuanchuan 
 * @date:   2018年12月3日 下午3:08:23
 */
public class PayScanReqData {
    /*支付渠道*/
    private String providerCode;
    /*支付来源，如自助机、移动端、官微等对应的code*/
    private String sourceCode;
    /* 交易金额*/
    private BigDecimal amount;
    /*申请识别号*/
    private String requestNo = "";
    /* 返回地址*/
    private String location = "";
    /* 商品描述*/
    private String body = "";
    /*令牌标识*/
    private String token = "";
    /*授权因子*/
    private String authCode = "";
    /*签名*/
    private String sign = "";

    
    
    public PayScanReqData() {
	}

	public PayScanReqData(String providerCode, String sourceCode, BigDecimal amount, String requestNo, String location,
			String body, String token) {
		this.providerCode = providerCode;
		this.sourceCode = sourceCode;
		this.amount = amount;
		this.requestNo = requestNo;
		this.location = location;
		this.body = body;
		this.token = token;
	}

	public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
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

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
}
