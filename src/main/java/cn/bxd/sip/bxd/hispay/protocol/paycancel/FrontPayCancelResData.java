package cn.bxd.sip.bxd.hispay.protocol.paycancel;

import lombok.Data;

/**
 * Description:扯淡响应
 * User: HaoMeiLing
 * Date: 2019-04-28
 * Time: 11:01
 */
@Data
public class FrontPayCancelResData {
    private String RefundState;//支付退费状态
    private String AdditionNotes;//如果退费失败，返回失败原因
    private String orderNo;//订单号
}
