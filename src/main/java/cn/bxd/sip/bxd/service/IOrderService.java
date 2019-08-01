package cn.bxd.sip.bxd.service;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2018-09-18
 * Time: 16:32
 */

import cn.bxd.sip.bxd.model.dto.OrderDBResInfo;
import cn.bxd.sip.bxd.model.entity.Order;

/**
 * @Author hml
 * @Description: 订单记录表
 * @Date 2015/8/6
 */

public interface IOrderService {
    /**
     * 查询订单
     *
     * @param orderId 订单编码
     * @return
     */
    Order getOrder(Long orderId);
    
    public int updateByPrimaryKey(Order order);


    /**
     * 支付完成后对订单的业务处理
     *
     * @param orderId 订单编码
     * @param transId 交易编码
     * @param result  处理结果 SUCCESS/FALI
     * @return 处理结果
     * @Author 郝美玲
     * @Date 20161212
     */
    OrderDBResInfo orderPayResult(Long orderId, Long transId, Short transTypeId, String result);


    /**
     * 支付完成后对订单的业务处理
     *
     * @param orderId 订单编码
     * @param transId 交易ID
     * @return 处理结果SUCCESS/FALI
     * @Author 郝美玲
     * @Date 20161212
     */
    OrderDBResInfo orderPayPre(Long orderId, Long transId);


    /**
     * 更新社保相关信息
     *
     * @param order
     * @return
     * @throws Exception
     */
    int updateOrderWithMedicare(Order order);
}

