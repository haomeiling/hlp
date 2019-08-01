package cn.bxd.sip.bxd.hispay.protocol.paymicro;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 刷卡支付出参
 * 
 * @date 20180118
 * @author haomeiling
 */
@XStreamAlias("BODY")
public class FrontPayMicroResData {
	private String OrderRefNo;// 订单参考号
	private Integer RequestDate;// 请求日期
	private String OrderNo = "";// 平台订单编号
	private String TransId;// 交易号
	private String TransactionID;// 微信/支付宝交易号
	private String TransState;// 交易状态
	private String AdditionNotes;// 附言

	public String getOrderRefNo() {
		return OrderRefNo;
	}

	public void setOrderRefNo(String orderRefNo) {
		OrderRefNo = orderRefNo;
	}

	public Integer getRequestDate() {
		return RequestDate;
	}

	public void setRequestDate(Integer requestDate) {
		RequestDate = requestDate;
	}

	public String getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}

	public String getTransId() {
		return TransId;
	}

	public void setTransId(String transId) {
		TransId = transId;
	}

	public String getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}

	public String getTransState() {
		return TransState;
	}

	public void setTransState(String transState) {
		TransState = transState;
	}

	public String getAdditionNotes() {
		return AdditionNotes;
	}

	public void setAdditionNotes(String additionNotes) {
		AdditionNotes = additionNotes;
	}

	@Override
	public String toString() {
		return "FrontPayMicroResData [OrderRefNo=" + OrderRefNo
				+ ", RequestDate=" + RequestDate + ", OrderNo=" + OrderNo
				+ ", TransId=" + TransId + ", TransactionID=" + TransactionID
				+ ", TransState=" + TransState + ", AdditionNotes="
				+ AdditionNotes + "]";
	}

}
