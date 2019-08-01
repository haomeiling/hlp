package cn.bxd.sip.bxd.apppay;

import java.math.BigDecimal;

/**
 * 刷卡支付请求
 * 
 * @date 20180118
 * @author haomeiling
 */
public class PayMicroReqData {

	/** 医院编码 */
	private int hospitalId;

	/** 订单ID */
	private Long orderId;

	/** 金额 */
	private BigDecimal amount;

	/** 商品描述 */
	private String body;

	/** 授权码 用于刷卡支付 */
	private String authCode;

	public PayMicroReqData(int hospitalId, Long orderId, BigDecimal amount,
			String body, String authCode) {
		super();
		this.hospitalId = hospitalId;
		this.orderId = orderId;
		this.amount = amount;
		this.body = body;
		this.authCode = authCode;
	}

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	@Override
	public String toString() {
		return "PayMicroReqData [hospitalId=" + hospitalId + ", orderId="
				+ orderId + ", amount=" + amount + ", body=" + body
				+ ", authCode=" + authCode + "]";
	}

}
