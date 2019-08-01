package cn.bxd.sip.bxd.hispay.protocol.paymicro;

import lombok.Data;
/**
 * 刷卡支付请求
 * @date 20180118
 * @author haomeiling
 */
/**
 *
 * { "msgNo":"2120082018011817565913862", "hosId":582,
 * "sessionId":"0a77044fe3714129b19593b4004bf37b", "Body":"宝信迪刷卡支付-测试 ",
 * "Amount":"1", "CasherName":"李鑫", "OrderTypeID":"2", "AdditionNotes":"测试",
 * "AuthCode":"136147395858228356", "RequestDate":"20180103",
 * "OrderRefNo":"20001", "CasherCode":"1234567890", "RequestTime":"103143" }
 *
 */
@Data
public class FrontPayMicroReqData {
	private String OrderRefNo;// 订单参考号

	private String RequestDate;// 请求日期

	private String RequestTime;// 请求时间

	private String AdditionNotes;// 附言

	private String Amount;// 交易金额

	private String OrderTypeID;// 订单类型

	private String AuthCode;// 授权码

	private String Body;// 商品描述

	private String CasherName;// 收费员姓名

	private String CasherCode;// 收费员编码


}
