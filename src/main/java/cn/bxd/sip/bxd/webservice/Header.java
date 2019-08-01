package cn.bxd.sip.bxd.webservice;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @Version 1.0
 * @Author haomeiling
 * @Date 2018-03-02
 */
@XStreamAlias("HEADER")
public class Header {

	private String Version;// 报文规范版本号 如：V1.0
	private String MsgType;// 消息种类
	private String MsgID;// 消息唯一标识
	private String AppID;// 应用ID 8位数字，由综合外联平台统一分配。 如：45090001、45090002
	private String MsgTime;// 消息产生的时间：YYYYMMDDHHMISS
	private String ResultCode;// 应答消息的结果编码，参见结果编码表
	private String ResultMsg;// 结果消息

	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getMsgID() {
		return MsgID;
	}
	public void setMsgID(String msgID) {
		MsgID = msgID;
	}
	public String getAppID() {
		return AppID;
	}
	public void setAppID(String appID) {
		AppID = appID;
	}
	public String getMsgTime() {
		return MsgTime;
	}
	public void setMsgTime(String msgTime) {
		MsgTime = msgTime;
	}
	public String getResultCode() {
		return ResultCode;
	}
	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}
	public String getResultMsg() {
		return ResultMsg;
	}
	public void setResultMsg(String resultMsg) {
		ResultMsg = resultMsg;
	}

}
