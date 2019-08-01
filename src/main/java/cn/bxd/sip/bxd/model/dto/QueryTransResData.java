package cn.bxd.sip.bxd.model.dto;

import java.util.List;


public class QueryTransResData {
    private String returnCode;
    private String returnMsg;

    /*签名*/
    private String sign;

    private List<TransData> transRecord;
    
    

	public QueryTransResData() {
	}

	public QueryTransResData(String returnCode, String returnMsg) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public List<TransData> getTransRecord() {
		return transRecord;
	}

	public void setTransRecord(List<TransData> transRecord) {
		this.transRecord = transRecord;
	}
}
