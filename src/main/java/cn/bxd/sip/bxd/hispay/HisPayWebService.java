package cn.bxd.sip.bxd.hispay;

import javax.jws.WebService;

/**
 * Description: 线下扫码支付和刷卡支付入口
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 14:52
 */
@WebService
public interface HisPayWebService {
    /**
     * 消息接收
     *
     * @param inMsgStr
     * @return
     */
     String recvMsg(String inMsgStr);
}
