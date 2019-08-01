package cn.bxd.sip.bxd.model.dto;

import lombok.Data;

@Data
public class PayCancelReqData {
	/*请求识别码*/
	private String requestNo;
    /*令牌标识*/
    private String token;
    /*授权因子*/
    private String authCode;
    /*签名*/
    private String sign;
	/*原因*/
	private String reason;
	public PayCancelReqData(String requestNo, String token, String reason) {
		this.requestNo = requestNo;
		this.token = token;
		this.reason = reason;
	}
	public PayCancelReqData() {
	}
	
	
}
