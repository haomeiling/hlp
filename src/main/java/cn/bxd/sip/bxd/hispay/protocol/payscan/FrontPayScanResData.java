package cn.bxd.sip.bxd.hispay.protocol.payscan;

import lombok.Data;

import java.util.List;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-04-28
 * Time: 13:37
 */
@Data
public class FrontPayScanResData {
    private String OrderRefNo;//	业务订单号	String(20)	缴费通知单号或计费单号
    private String RequestDate;//	请求日期	String(8)	YYYYMMDD
    private String OrderNo;//	订单编号	String(20)
    private String ProviderCount;//	错误支付渠道数量	String(4)
    List<PayProvider> ProviderList;//ProviderList	支付渠道列表
}
