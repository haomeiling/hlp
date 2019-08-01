package cn.bxd.sip.bxd.hispay.protocol.payquery;


import lombok.Data;

/**
 * 支付查询
 *
 * @author HaoMeiLing
 * @date 2018年1月23日 下午3:02:36
 * @version 1.0
 */
@Data
public class FrontPayQueryReqData {
	// =============以下是协议返回=================

	private String OrderRefNo;// 医院订单号

	private String OrderTypeID;// 1-预约,2-诊间支付,3-住院押金，4-改签，6-签到，7-挂号

	private String AdditionNotes;// 附言

	private String OrderNo;// 掌上医院订单号

	private String Amount;// 金额

}
