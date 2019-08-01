package cn.bxd.sip.bxd.service;

import cn.bxd.sip.bxd.var.OrderStatus;

/**
 * @author:tangliang
 * @date:2018/8/3
 * @description:
 */
public interface IOrderStatusService {
    int insertOrderStatus(Long orderId, OrderStatus orderStatus, String mark);
}
