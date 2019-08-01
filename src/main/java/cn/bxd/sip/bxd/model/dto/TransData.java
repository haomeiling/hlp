package cn.bxd.sip.bxd.model.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TransData {
	
	/* 交易流水号 */
    private Long transId;

	/* 原交易流水号 */
	private Long originalTransId;

    /* 医院订单号 */
    private String hisOrderNo;

    /* 平台订单号 */
    private String orderNo;

    /* 交易时间 */
    private String transTime;
    
    private String endTime;
    
    private int orderType;

    /* 交易金额 */
    private BigDecimal amount;

    /* 退费第三方交易流水号 */
    private String transMessage;

    /* 支付类型 */
    private String providerCode;

    /* 订单类型 */
    private Integer transScene;

    /* 机构代号  */
    private String AccountId;

    /* 第三方交易流水号 */
    private String transactionId;

    /* 交易状态 */
    private String transStatus;
    
    /** 医院id**/
    private String hospitalCode;

	/* 支付类型  2019/04/09 haomeiling  与文档不一致*/
	private Integer transTypeId;


	public Long getTransId() {
		return transId;
	}

	public void setTransId(Long transId) {
		this.transId = transId;
	}

	public String getHisOrderNo() {
		return hisOrderNo;
	}

	public void setHisOrderNo(String hisOrderNo) {
		this.hisOrderNo = hisOrderNo;
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


	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public Integer getTransScene() {
		return transScene;
	}

	public void setTransScene(Integer transScene) {
		this.transScene = transScene;
	}

	public String getAccountId() {
		return AccountId;
	}

	public void setAccountId(String accountId) {
		AccountId = accountId;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTransMessage() {
		return transMessage;
	}

	public void setTransMessage(String transMessage) {
		this.transMessage = transMessage;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public Integer getTransTypeId() {
		return transTypeId;
	}

	public void setTransTypeId(Integer transTypeId) {
		this.transTypeId = transTypeId;
	}

	public Long getOriginalTransId() {
		return originalTransId;
	}

	public void setOriginalTransId(Long originalTransId) {
		this.originalTransId = originalTransId;
	}
}
