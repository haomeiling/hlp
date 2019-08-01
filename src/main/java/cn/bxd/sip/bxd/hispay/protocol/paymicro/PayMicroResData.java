package cn.bxd.sip.bxd.hispay.protocol.paymicro;

import java.math.BigDecimal;

/**
 * 请求结果返回
 * 
 * @date 20180118
 * @author haomeiling
 */
public class PayMicroResData {

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

	/*交易流水号ID*/
	private Long transId;
	/* 交易金额*/
	private BigDecimal amount;
	/*支付渠道*/
	private String providerCode;
	/*请求编码*/
	private String requestNo;
	/*支付跳转链接*/
	private String transactionId;//第三方交易流水号


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

	public Long getTransId() {
		return transId;
	}

	public void setTransId(Long transId) {
		this.transId = transId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	@Override
	public String toString() {
		return "PayMicroResData{" +
				"returnCode='" + returnCode + '\'' +
				", returnMsg='" + returnMsg + '\'' +
				", resultCode='" + resultCode + '\'' +
				", resultMsg='" + resultMsg + '\'' +
				", errCode='" + errCode + '\'' +
				", sign='" + sign + '\'' +
				", transId=" + transId +
				", amount=" + amount +
				", providerCode='" + providerCode + '\'' +
				", requestNo='" + requestNo + '\'' +
				", transactionId='" + transactionId + '\'' +
				'}';
	}
}
