package cn.bxd.sip.bxd.hispay.protocol.paycancel;

import lombok.Data;

/**
 * Description:撤单请求
 * User: HaoMeiLing
 * Date: 2019-04-28
 * Time: 11:01
 */
@Data
public class FrontPayCancelReqData {
    private String OrderNo;//具有唯一标识的支付组件的订单编号，不是业务订单号。
    private String AdditionNotes;//撤销原因
    private String Amount;//金额 单位元

}
