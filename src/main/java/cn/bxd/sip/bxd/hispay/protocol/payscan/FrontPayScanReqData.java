package cn.bxd.sip.bxd.hispay.protocol.payscan;

import lombok.Data;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-04-28
 * Time: 13:37
 */
@Data
public class FrontPayScanReqData {
    private String OrderRefNo;//	业务订单号	String(20)	缴费通知单号或计费单号	是
    private String  RequestDate;//	请求日期	String(8)	YYYYMMDD	是
    private String  RequestTime;//	请求时间	String(6)	HH24MMSS	是
    private String  AdditionNotes;//	附言	String(60)		是
    private String  Amount;//	交易金额	String(12)	N12.2 	是
    private String  OrderTypeID;//	交易类型	String(12)	7-	预约,2-诊间支付,3-住院押金，4-改签，6-签到，7-挂号，8-停车	是
}
