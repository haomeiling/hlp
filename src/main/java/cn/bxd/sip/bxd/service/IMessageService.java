package cn.bxd.sip.bxd.service;


/**
 * 微信消息服务类
 *
 * @author HML
 * @Date 2016/3/15
 */
public interface IMessageService {
    /**
     * 挂号取消通知
     * @param hospitalId  医院ID
     * @param userId  用户ID
     * @param orderId 订单编号
     */
    void sendCancelNotify(Integer hospitalId,Integer userId,Long orderId);
}
