package cn.bxd.sip.bxd.hispay.protocol.payquery;

import java.util.List;

/**
 * @author HaoMeiLing
 * @date 2018年1月23日 下午4:07:50
 * @version 1.0
 */
public class PayQueryTopResData {
	private String operation;
	private String returnCode;
	private String returnMsg;
	private List<PayQueryResData> transRecord;

	public PayQueryTopResData(String returnCode) {
		super();
		this.returnCode = returnCode;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

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

	public List<PayQueryResData> getTransRecord() {
		return transRecord;
	}

	public void setTransRecord(List<PayQueryResData> transRecord) {
		this.transRecord = transRecord;
	}

}
