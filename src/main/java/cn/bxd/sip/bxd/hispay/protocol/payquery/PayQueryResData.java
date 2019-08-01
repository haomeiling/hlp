package cn.bxd.sip.bxd.hispay.protocol.payquery;

import java.math.BigDecimal;

/**
 * @author HaoMeiLing
 * @date 2018年1月23日 下午3:37:36
 * @version 1.0
 */
public class PayQueryResData {
	/**
	 * 交易流水号
	 */
	private Long transId;

	/**
	 * 医院订单号
	 */
	private String hisOrderNo;

	/**
	 * 医院订单号
	 */
	private Long orderNo;

	/**
	 * 交易时间
	 */
	private String transTime;

	/**
	 * 交易金额
	 */
	private BigDecimal amount;

	/**
	 * 交易结果说明
	 */
	private String transMessage;

	/**
	 * 支付渠道代号名称 WECHAT/ALIPAY
	 */
	private String providerCode;

	/**
	 * 机构代号
	 */
	private Integer hospitalCode;

	/**
	 * 第三方交易流水号
	 */
	private String transactionId;

	/**
	 * 交易状态
	 */
	private String transStatus;

	@Override
	public String toString() {
		return "QueryTrans{" + "transId=" + transId + ", hisOrderNo="
				+ hisOrderNo + ", orderNo=" + orderNo + ", transTime="
				+ transTime + ", amount=" + amount + ", transMessage='"
				+ transMessage + '\'' + ", providerCode='" + providerCode
				+ '\'' + ", hospitalCode=" + hospitalCode + ", transactionId='"
				+ transactionId + '\'' + ", transStatus='" + transStatus + '\''
				+ '}';
	}

	public String getHisOrderNo() {
		return hisOrderNo;
	}

	public void setHisOrderNo(String hisOrderNo) {
		this.hisOrderNo = hisOrderNo;
	}

	public Long getTransId() {
		return transId;
	}

	public void setTransId(Long transId) {
		this.transId = transId;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTransMessage() {
		return transMessage == null ? "֧���ɹ�" : transMessage;
	}

	public void setTransMessage(String transMessage) {
		this.transMessage = transMessage;
	}

	public String getProviderCode() {
		return ("1").equals(providerCode) ? "WECHAT" : "ALIPAY";
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public Integer getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(Integer hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
}
