package cn.bxd.sip.bxd.hispay.protocol.payquery;

import lombok.Data;

/**
 * @author HaoMeiLing
 * @date 2018年1月23日 下午3:03:11
 * @version 1.0
 */
@Data
public class FrontPayQueryResData {
	private String OrderRefNo;// 订单参考号
	private String OrderTypeID;// 订单类型
	private String TransState;// 交易状态
	private String OrderNo = "";// 平台订单编号
	private String TransId;// 交易号
	private String TransactionID;// 微信/支付宝交易号
	private String AdditionNotes;// 附言
	private String PayTime;// 支付时间

}
