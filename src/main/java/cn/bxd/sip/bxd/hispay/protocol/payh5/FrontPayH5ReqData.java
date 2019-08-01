package cn.bxd.sip.bxd.hispay.protocol.payh5;

import java.math.BigDecimal;

/**
 * H5支付请求
 * 
 * @date 20180227
 * @author haomeiling
 */
public class FrontPayH5ReqData {
	private int hosId;// 重新封裝后的医院ID
	private String sessionId;// 缓存
	private String msgNo;// 消息编号

	// =============以下是协议返回=================

	private String OrderRefNo;// 订单参考号

	private Integer RequestDate;// 请求日期

	private Integer RequestTime;// 请求时间

	private String AdditionNotes;// 附言

	private BigDecimal Amount;// 交易金额

	private Short OrderTypeID;// 订单类型

	public int getHosId() {
		return hosId;
	}

	public void setHosId(int hosId) {
		this.hosId = hosId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getMsgNo() {
		return msgNo;
	}

	public void setMsgNo(String msgNo) {
		this.msgNo = msgNo;
	}

	public String getOrderRefNo() {
		return OrderRefNo;
	}

	public void setOrderRefNo(String orderRefNo) {
		OrderRefNo = orderRefNo;
	}

	public String getAdditionNotes() {
		return AdditionNotes;
	}

	public void setAdditionNotes(String additionNotes) {
		AdditionNotes = additionNotes;
	}

	public Integer getRequestDate() {
		return RequestDate;
	}

	public void setRequestDate(Integer requestDate) {
		RequestDate = requestDate;
	}

	public Integer getRequestTime() {
		return RequestTime;
	}

	public void setRequestTime(Integer requestTime) {
		RequestTime = requestTime;
	}

	public BigDecimal getAmount() {
		return Amount;
	}

	public void setAmount(BigDecimal amount) {
		Amount = amount;
	}

	public Short getOrderTypeID() {
		return OrderTypeID;
	}

	public void setOrderTypeID(Short orderTypeID) {
		OrderTypeID = orderTypeID;
	}

}
