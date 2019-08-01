package cn.bxd.sip.bxd.model.dto;

public class QueryPayStatusReqData {

	/*请求识别码*/
	private String requestNo;
    /*令牌标识*/
    private String token;
    /*授权因子*/
    private String authCode;
    /*签名*/
    private String sign;
    
    
    
    
	public QueryPayStatusReqData() {
	}
	
	
	public QueryPayStatusReqData(String requestNo, String token) {
		this.requestNo = requestNo;
		this.token = token;
	}


	public QueryPayStatusReqData(String requestNo, String token, String authCode, String sign) {
		this.requestNo = requestNo;
		this.token = token;
		this.authCode = authCode;
		this.sign = sign;
	}
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
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
