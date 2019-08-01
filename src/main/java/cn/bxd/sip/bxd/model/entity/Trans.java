package cn.bxd.sip.bxd.model.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class Trans {

	private Long transId;
	
	private Long originalTransId;
	
	private Date createTime;
	
	private Date startedTime;
	
	private Date endedTime;
	
	private Integer status;
	
	private BigDecimal amount;
	
	private String transCode;
	
	private String transMessage;
	
	private Integer providerId;
	
	private Integer transTypeId;
	
	private Integer hospitalId;
	
	private Integer userId;
	
	private Long orderId;
	
	private String transactionId;
	
	private Integer successFlag;
	
	private Integer withdrawFlag;
	
	private Integer retryCount;
	
	private Date lastRetryTime;
	
	private String appId;
	
	private String targetAccount;
	
	private String requestorCode;
	
	private String requestNo;
	
	private String openId;
	
	private Integer cancelFlag;
	
	private Integer platformId;

	
	
	public Trans() {
	}

	public Long getTransId() {
		return transId;
	}

	public void setTransId(Long transId) {
		this.transId = transId;
	}

	public Long getOriginalTransId() {
		return originalTransId;
	}

	public void setOriginalTransId(Long originalTransId) {
		this.originalTransId = originalTransId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStartedTime() {
		return startedTime;
	}

	public void setStartedTime(Date startedTime) {
		this.startedTime = startedTime;
	}

	public Date getEndedTime() {
		return endedTime;
	}

	public void setEndedTime(Date endedTime) {
		this.endedTime = endedTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getTransMessage() {
		return transMessage;
	}

	public void setTransMessage(String transMessage) {
		this.transMessage = transMessage;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public Integer getTransTypeId() {
		return transTypeId;
	}

	public void setTransTypeId(Integer transTypeId) {
		this.transTypeId = transTypeId;
	}

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(Integer successFlag) {
		this.successFlag = successFlag;
	}

	public Integer getWithdrawFlag() {
		return withdrawFlag;
	}

	public void setWithdrawFlag(Integer withdrawFlag) {
		this.withdrawFlag = withdrawFlag;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	public Date getLastRetryTime() {
		return lastRetryTime;
	}

	public void setLastRetryTime(Date lastRetryTime) {
		this.lastRetryTime = lastRetryTime;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(String targetAccount) {
		this.targetAccount = targetAccount;
	}

	public String getRequestorCode() {
		return requestorCode;
	}

	public void setRequestorCode(String requestorCode) {
		this.requestorCode = requestorCode;
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

	public Integer getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Integer cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public Integer getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}
    
	
	
}
